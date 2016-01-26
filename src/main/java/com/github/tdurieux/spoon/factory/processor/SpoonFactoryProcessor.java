package com.github.tdurieux.spoon.factory.processor;

import com.github.tdurieux.spoon.factory.F;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtReturn;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
public class SpoonFactoryProcessor extends AbstractProcessor<CtType> {
	private List<Class> factories = new ArrayList<>();
	private List<CtMethod> factoryMethods = new ArrayList<>();
	private List<CtField> constants = new ArrayList<>();

	public SpoonFactoryProcessor() {
		Method[] declaredMethods = Factory.class.getDeclaredMethods();
		for (int i = 0; i < declaredMethods.length; i++) {
			Method method = declaredMethods[i];
			if(!method.getReturnType().getSimpleName().contains("Factory")) {
				continue;
			}
			factories.add(method.getReturnType());
		}
		System.out.println(factories);
	}

	@Override
	public boolean isToBeProcessed(CtType candidate) {
		try {
			return factories.contains(candidate.getActualClass());
		} catch (Throwable e) {
			return false;
		}
	}

	@Override
	public void process(CtType ctClass) {
		Set<CtMethod> methods = ctClass.getMethods();
		for (Iterator<CtMethod> iterator = methods.iterator(); iterator
				.hasNext(); ) {
			CtMethod ctMethod = iterator.next();
			if((ctMethod.hasModifier(ModifierKind.PUBLIC) || ctClass instanceof CtInterface) &&
					!ctMethod.getType().equals(getFactory().Type().VOID_PRIMITIVE) ) {
				factoryMethods.add(ctMethod);
			}
		}
		List<CtField> fields = ctClass.getFields();
		for (int i = 0; i < fields.size(); i++) {
			CtField ctField = fields.get(i);
			if(((ctField.hasModifier(ModifierKind.PUBLIC) && ctField.hasModifier(ModifierKind.STATIC))
					|| ctClass instanceof CtInterface)) {
				constants.add(ctField);
			}
		}
	}

	@Override
	public void processingDone() {
		F.factory = getFactory();

		CtClass<Object> FClass = F.createClass("com.github.tdurieux.spoon.factory.F");
		FClass.addModifier(ModifierKind.PUBLIC);
		Set<ModifierKind> modifiers = new HashSet<>();
		modifiers.add(ModifierKind.PUBLIC);
		modifiers.add(ModifierKind.STATIC);
		CtField<Object> factoryField = F.createField(null, modifiers,
				F.createCtTypeReference(Factory.class),
				"factory");
		FClass.addField(factoryField);

		for (int i = 0; i < constants.size(); i++) {
			CtField ctField = F.clone(constants.get(i));
			FClass.addField(ctField);
		}
		for (int i = 0; i < factoryMethods.size(); i++) {
			CtMethod ctMethod = factoryMethods.get(i);
			String name = ctMethod.getSimpleName();
			CtMethod methodFactory = F.clone(factoryMethods.get(i));
			if(name.equals("create") ||
					name.equals("get") ||
					name.equals("getAll") ||
					name.equals("createReference")) {
				name += ctMethod.getDeclaringType().getSimpleName().replace("Factory", "");
			}
			methodFactory.setAnnotations(Collections.EMPTY_LIST);
			methodFactory.setSimpleName(name);
			methodFactory.setModifiers(modifiers);
			methodFactory.setPosition(null);
			List<CtExpression<?>> args = new ArrayList<>();
			for (int j = 0; j < ctMethod.getParameters().size(); j++) {
				CtParameter ctParameter = (CtParameter) ctMethod.getParameters().get(j);
				args.add(F.createVariableRead(ctParameter.getReference(), false));
			}
			CtExpression<?> target;
			if(ctMethod.hasModifier(ModifierKind.STATIC)) {
				target = F.createTypeAccess(ctMethod.getDeclaringType().getReference());
			} else {
				target = F.createVariableRead(factoryField.getReference(), true);
				try {
					CtExecutableReference<Object> factoryGetter = F.createReferenceMethod(Factory.class
									.getDeclaredMethod(
											ctMethod.getDeclaringType()
													.getSimpleName()
													.replace("Factory", "")));
					target = F.createInvocation(target, factoryGetter);
				} catch (NoSuchMethodException e) {
					continue;
				}
			}
			CtInvocation ctInvocation = F.createInvocation(
					target,
					ctMethod.getReference(),
					args);
			CtReturn<Object> aReturn = F.createReturn().setReturnedExpression(ctInvocation);

			CtBlock block = F.createCtBlock(aReturn);
			methodFactory.setBody(block);
			FClass.addMethod(methodFactory);
		}
		super.processingDone();
	}
}
