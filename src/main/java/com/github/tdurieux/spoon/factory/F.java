package com.github.tdurieux.spoon.factory;

import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtAnnotationFieldAccess;
import spoon.reflect.code.CtArrayRead;
import spoon.reflect.code.CtArrayWrite;
import spoon.reflect.code.CtAssert;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtBreak;
import spoon.reflect.code.CtCase;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtCatchVariable;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtConditional;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtContinue;
import spoon.reflect.code.CtDo;
import spoon.reflect.code.CtExecutableReferenceExpression;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtFieldWrite;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLambda;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.code.CtNewClass;
import spoon.reflect.code.CtOperatorAssignment;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtStatementList;
import spoon.reflect.code.CtSuperAccess;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.code.CtSynchronized;
import spoon.reflect.code.CtThisAccess;
import spoon.reflect.code.CtThrow;
import spoon.reflect.code.CtTry;
import spoon.reflect.code.CtTryWithResource;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.code.CtVariableWrite;
import spoon.reflect.code.CtWhile;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.Import;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtAnnotationType;
import spoon.reflect.declaration.CtAnonymousExecutable;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtNamedElement;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.CtTypeParameter;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.eval.PartialEvaluator;
import spoon.reflect.factory.Factory;
import spoon.reflect.internal.CtCircularTypeReference;
import spoon.reflect.internal.CtImplicitArrayTypeReference;
import spoon.reflect.internal.CtImplicitTypeReference;
import spoon.reflect.reference.CtArrayTypeReference;
import spoon.reflect.reference.CtCatchVariableReference;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtLocalVariableReference;
import spoon.reflect.reference.CtPackageReference;
import spoon.reflect.reference.CtParameterReference;
import spoon.reflect.reference.CtReference;
import spoon.reflect.reference.CtTypeParameterReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtVariableReference;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class F {
    public static Factory factory;

    /** 
     * Adds an annotation to an element.
     * 
     *  @param element
     *  		the program element to annotate
     *  @param annotationType
     *  		the annotation type
     *  @return the concerned annotation
     */
    public static <A extends Annotation>CtAnnotation<A> annotate(CtElement element, Class<A> annotationType) {
        return factory.Annotation().annotate(element, annotationType);
    }

    /** 
     * Creates/updates an element's annotation value.
     * 
     *  @param element
     *  		the program element to annotate
     *  @param annotationType
     *  		the annotation type
     *  @param annotationElementName
     *  		the annotation element name
     *  @param value
     *  		the value of the annotation element
     *  @return the created/updated annotation
     */
    public static <A extends Annotation>CtAnnotation<A> annotate(CtElement element, Class<A> annotationType, String annotationElementName, Object value) {
        return factory.Annotation().annotate(element, annotationType, annotationElementName, value);
    }

    /** 
     * Adds an annotation to an element.
     * 
     *  @param element
     *  		the program element to annotate
     *  @param annotationType
     *  		the annotation type
     *  @return the concerned annotation
     */
    public static <A extends Annotation>CtAnnotation<A> annotate(CtElement element, CtTypeReference<A> annotationType) {
        return factory.Annotation().annotate(element, annotationType);
    }

    /** 
     * Creates/updates an element's annotation value.
     * 
     *  @param element
     *  		the program element to annotate
     *  @param annotationType
     *  		the annotation type
     *  @param annotationElementName
     *  		the annotation element name
     *  @param value
     *  		the value of the annotation element
     *  @return the created/updated annotation
     */
    public static <A extends Annotation>CtAnnotation<A> annotate(CtElement element, CtTypeReference<A> annotationType, String annotationElementName, Object value) {
        return factory.Annotation().annotate(element, annotationType, annotationElementName, value);
    }

    /** 
     * Creates an annotation.
     */
    public static <A extends Annotation>CtAnnotation<A> createAnnotation() {
        return factory.Core().createAnnotation();
    }

    /** 
     * Creates a variable assignment (can be an expression or a statement).
     * 
     *  @param <T>
     *  		the type of the assigned variable
     *  @param variable
     *  		a reference to the assigned variable
     *  @param isStatic
     *  		tells if the assigned variable is static or not
     *  @param expression
     *  		the assigned expression
     *  @return a variable assignment
     */
    public static <A, T extends A>CtAssignment<A, T> createVariableAssignment(CtVariableReference<A> variable, boolean isStatic, CtExpression<T> expression) {
        return factory.Code().createVariableAssignment(variable, isStatic, expression);
    }

    /** 
     * Gets a list of references from a list of elements.
     * 
     *  @param <R>
     *  		the expected reference type
     *  @param <E>
     *  		the element type
     *  @param elements
     *  		the element list
     *  @return the corresponding list of references
     */
    public static <R extends CtReference, E extends CtNamedElement>List<R> getReferences(List<E> elements) {
        return factory.Code().getReferences(elements);
    }

    /** 
     * Creates a method.
     * 
     *  @param target
     *  		the class where the method is inserted
     *  @param modifiers
     *  		the modifiers
     *  @param returnType
     *  		the method's return type
     *  @param name
     *  		the method's name
     *  @param parameters
     *  		the parameters
     *  @param thrownTypes
     *  		the thrown types
     *  @param body
     *  		the method's body
     */
    public static <R, B extends R>CtMethod<R> createMethod(CtClass<?> target, Set<ModifierKind> modifiers, CtTypeReference<R> returnType, String name, List<CtParameter<?>> parameters, Set<CtTypeReference<? extends Throwable>> thrownTypes, CtBlock<B> body) {
        return factory.Method().create(target, modifiers, returnType, name, parameters, thrownTypes, body);
    }

    /** 
     * Creates a block.
     */
    public static <R>CtBlock<R> createBlock() {
        return factory.Core().createBlock();
    }

    /** 
     * Creates a <code>return</code> statement.
     */
    public static <R>CtReturn<R> createReturn() {
        return factory.Core().createReturn();
    }

    /** 
     * Creates a statement list.
     */
    public static <R>CtStatementList createStatementList() {
        return factory.Core().createStatementList();
    }

    /** 
     * Creates a new statement list from an existing block.
     */
    public static <R>CtStatementList createStatementList(CtBlock<R> block) {
        return factory.Code().createStatementList(block);
    }

    /** 
     * Creates a <code>case</code> clause.
     */
    public static <S>CtCase<S> createCase() {
        return factory.Core().createCase();
    }

    /** 
     * Creates a <code>switch</code> statement.
     */
    public static <S>CtSwitch<S> createSwitch() {
        return factory.Core().createSwitch();
    }

    /** 
     * Creates an enum.
     */
    public static <T extends Enum<?>>CtEnum<T> createEnum() {
        return factory.Core().createEnum();
    }

    /** 
     * Gets a class from its runtime Java class.
     * 
     *  @param <T>
     *  		type of created class
     *  @param cl
     *  		the java class: note that this class should be Class&lt;T&gt; but it
     *  		then poses problem when T is a generic type itself
     */
    public static <T extends Enum<?>>CtEnum<T> getEnum(Class<T> cl) {
        return factory.Enum().getEnum(cl);
    }

    /** 
     * Creates an annotation type.
     * 
     *  @param owner
     *  		the package of the annotation type
     *  @param simpleName
     *  		the name of annotation
     */
    public static <T extends Annotation>CtAnnotationType<?> createAnnotation(CtPackage owner, String simpleName) {
        return factory.Annotation().create(owner, simpleName);
    }

    /** 
     * Creates an annotation type.
     */
    public static <T extends Annotation>CtAnnotationType<T> createAnnotationType() {
        return factory.Core().createAnnotationType();
    }

    /** 
     * Gets a annotation type from its name.
     */
    public static <T extends Annotation>CtType<T> getAnnotationType(String qualifiedName) {
        return factory.Annotation().getAnnotationType(qualifiedName);
    }

    /** 
     * Creates a block.
     * 
     *  @param element
     *  		Statement of the block.
     *  @param <T>
     *  		Subclasses of CtStatement.
     *  @return a block.
     */
    public static <T extends CtStatement>CtBlock<?> createCtBlock(T element) {
        return factory.Code().createCtBlock(element);
    }

    /** 
     * Creates an assignment expression.
     */
    public static <T, A extends T>CtAssignment<T, A> createAssignment() {
        return factory.Core().createAssignment();
    }

    /** 
     * Creates a new operator assignment (like +=).
     */
    public static <T, A extends T>CtOperatorAssignment<T, A> createOperatorAssignment() {
        return factory.Core().createOperatorAssignment();
    }

    /** 
     * Creates a new executable reference expression.
     */
    public static <T, E extends CtExpression<?>>CtExecutableReferenceExpression<T, E> createExecutableReferenceExpression() {
        return factory.Core().createExecutableReferenceExpression();
    }

    /** 
     * Recursively clones a given element of the metamodel and all its child
     *  elements.
     * 
     *  @param <T>
     *  		the element's type
     *  @param element
     *  		the element
     *  @return a clone of <code>element</code>
     */
    public static <T>T clone(T element) {
        return factory.Core().clone(element);
    }

    /** 
     * Create an access to annotation value
     * 
     *  @return
     */
    public static <T>CtAnnotationFieldAccess<T> createAnnotationFieldAccess() {
        return factory.Core().createAnnotationFieldAccess();
    }

    /** 
     * Creates an array read access expression.
     */
    public static <T>CtArrayRead<T> createArrayRead() {
        return factory.Core().createArrayRead();
    }

    /** 
     * Creates an array write access expression.
     */
    public static <T>CtArrayWrite<T> createArrayWrite() {
        return factory.Core().createArrayWrite();
    }

    /** 
     * Creates an <code>assert</code> statement.
     */
    public static <T>CtAssert<T> createAssert() {
        return factory.Core().createAssert();
    }

    /** 
     * Creates a binary operator.
     */
    public static <T>CtBinaryOperator<T> createBinaryOperator() {
        return factory.Core().createBinaryOperator();
    }

    /** 
     * Creates a binary operator.
     * 
     *  @param <T>
     *  		the type of the expression
     *  @param left
     *  		the left operand
     *  @param right
     *  		the right operand
     *  @param kind
     *  		the operator kind
     *  @return a binary operator expression
     */
    public static <T>CtBinaryOperator<T> createBinaryOperator(CtExpression<?> left, CtExpression<?> right, BinaryOperatorKind kind) {
        return factory.Code().createBinaryOperator(left, right, kind);
    }

    /** 
     * Creates a catch variable declaration statement.
     */
    public static <T>CtCatchVariable<T> createCatchVariable() {
        return factory.Core().createCatchVariable();
    }

    /** 
     * Creates a catch variable declaration.
     * 
     *  @param <T>
     *  		the catch variable type
     *  @param type
     *  		the reference to the type
     *  @param name
     *  		the name of the variable
     *  @return a new catch variable declaration
     */
    public static <T>CtCatchVariable<T> createCatchVariable(CtTypeReference<T> type, String name) {
        return factory.Code().createCatchVariable(type, name);
    }

    /** 
     * Creates a code snippet expression.
     */
    public static <T>CtCodeSnippetExpression<T> createCodeSnippetExpression() {
        return factory.Core().createCodeSnippetExpression();
    }

    /** 
     * Creates a Code Snippet expression.
     * 
     *  @param <T>
     *  		The type of the expression represented by the CodeSnippet
     *  @param expression
     *  		The string that contains the expression.
     *  @return a new CtCodeSnippetExpression.
     */
    public static <T>CtCodeSnippetExpression<T> createCodeSnippetExpression(String expression) {
        return factory.Code().createCodeSnippetExpression(expression);
    }

    /** 
     * Creates a conditional expression (<code>boolExpr?ifTrue:ifFalse</code>).
     */
    public static <T>CtConditional<T> createConditional() {
        return factory.Core().createConditional();
    }

    /** 
     * Creates a constructor call expression.
     */
    public static <T>CtConstructorCall<T> createConstructorCall() {
        return factory.Core().createConstructorCall();
    }

    /** 
     * Creates a constructor call. The correct constructor is inferred based on parameters
     * 
     *  @param type the decelerating type of the constructor
     *  @param parameters the arguments of the constructor call
     *  @param <T> the actual type of the decelerating type of the constructor if available
     *  @return the constructor call
     */
    public static <T>CtConstructorCall<T> createConstructorCall(CtTypeReference<T> type, CtExpression<?>... parameters) {
        return factory.Code().createConstructorCall(type, parameters);
    }

    /** 
     * Creates a class access expression of the form <code>C.class</code>.
     * 
     *  @param <T>
     *  		the actual type of the accessed class if available
     *  @param type
     *  		a type reference to the accessed class
     *  @return the class access expression.
     */
    public static <T>CtFieldAccess<Class<T>> createClassAccess(CtTypeReference<T> type) {
        return factory.Code().createClassAccess(type);
    }

    /** 
     * Creates a field read access.
     */
    public static <T>CtFieldRead<T> createFieldRead() {
        return factory.Core().createFieldRead();
    }

    /** 
     * Creates a field write access.
     */
    public static <T>CtFieldWrite<T> createFieldWrite() {
        return factory.Core().createFieldWrite();
    }

    /** 
     * Creates an invocation expression.
     */
    public static <T>CtInvocation<T> createInvocation() {
        return factory.Core().createInvocation();
    }

    /** 
     * Creates an invocation (can be a statement or an expression).
     * 
     *  @param <T>
     *  		the return type of the invoked method
     *  @param target
     *  		the target expression (may be null for static methods)
     *  @param executable
     *  		the invoked executable
     *  @param arguments
     *  		the argument list
     *  @return the new invocation
     */
    public static <T>CtInvocation<T> createInvocation(CtExpression<?> target, CtExecutableReference<T> executable, List<CtExpression<?>> arguments) {
        return factory.Code().createInvocation(target, executable, arguments);
    }

    /** 
     * Creates an invocation (can be a statement or an expression).
     * 
     *  @param <T>
     *  		the return type of the invoked method
     *  @param target
     *  		the target expression
     *  @param executable
     *  		the invoked executable
     *  @param arguments
     *  		the argument list
     *  @return the new invocation
     */
    public static <T>CtInvocation<T> createInvocation(CtExpression<?> target, CtExecutableReference<T> executable, CtExpression<?>... arguments) {
        return factory.Code().createInvocation(target, executable, arguments);
    }

    /** 
     * Creates a new anonymous method expression.
     */
    public static <T>CtLambda<T> createLambda() {
        return factory.Core().createLambda();
    }

    /** 
     * Creates a literal expression.
     */
    public static <T>CtLiteral<T> createLiteral() {
        return factory.Core().createLiteral();
    }

    /** 
     * Creates a literal with a given value.
     * 
     *  @param <T>
     *  		the type of the literal
     *  @param value
     *  		the value of the literal
     *  @return a new literal
     */
    public static <T>CtLiteral<T> createLiteral(T value) {
        return factory.Code().createLiteral(value);
    }

    /** 
     * Creates a local variable declaration statement.
     */
    public static <T>CtLocalVariable<T> createLocalVariable() {
        return factory.Core().createLocalVariable();
    }

    /** 
     * Creates a local variable declaration.
     * 
     *  @param <T>
     *  		the local variable type
     *  @param type
     *  		the reference to the type
     *  @param name
     *  		the name of the variable
     *  @param defaultExpression
     *  		the assigned default expression
     *  @return a new local variable declaration
     */
    public static <T>CtLocalVariable<T> createLocalVariable(CtTypeReference<T> type, String name, CtExpression<T> defaultExpression) {
        return factory.Code().createLocalVariable(type, name, defaultExpression);
    }

    /** 
     * Creates a one-dimension array that must only contain literals.
     */
    public static <T>CtNewArray<T[]> createLiteralArray(T[] value) {
        return factory.Code().createLiteralArray(value);
    }

    /** 
     * Creates a new array expression.
     */
    public static <T>CtNewArray<T> createNewArray() {
        return factory.Core().createNewArray();
    }

    /** 
     * Creates a new anonymous class expression.
     */
    public static <T>CtNewClass<T> createNewClass() {
        return factory.Core().createNewClass();
    }

    /** 
     * Creates a list of statements that contains the assignments of a set of
     *  variables.
     * 
     *  @param variables
     *  		the variables to be assigned
     *  @param expressions
     *  		the assigned expressions
     *  @return a list of variable assignments
     */
    public static <T>CtStatementList createVariableAssignments(List<? extends CtVariable<T>> variables, List<? extends CtExpression<T>> expressions) {
        return factory.Code().createVariableAssignments(variables, expressions);
    }

    /** 
     * Creates an access expression to super.
     */
    public static <T>CtSuperAccess<T> createSuperAccess() {
        return factory.Core().createSuperAccess();
    }

    /** 
     * Creates an access expression to this.
     */
    public static <T>CtThisAccess<T> createThisAccess() {
        return factory.Core().createThisAccess();
    }

    /** 
     * Creates an access to a <code>this</code> variable (of the form
     *  <code>type.this</code>).
     * 
     *  @param <T>
     *  		the actual type of <code>this</code>
     *  @param type
     *  		the reference to the type that holds the <code>this</code>
     *  		variable
     *  @return a <code>type.this</code> expression
     */
    public static <T>CtThisAccess<T> createThisAccess(CtTypeReference<T> type) {
        return factory.Code().createThisAccess(type);
    }

    /** 
     * Creates a type access expression.
     */
    public static <T>CtTypeAccess<T> createTypeAccess() {
        return factory.Core().createTypeAccess();
    }

    /** 
     * Creates a accessed type.
     *  @param accessedType a type reference to the accessed type.
     *  @param <T> the type of the expression.
     *  @return a accessed type expression.
     */
    public static <T>CtTypeAccess<T> createTypeAccess(CtTypeReference<T> accessedType) {
        return factory.Code().createTypeAccess(accessedType);
    }

    /** 
     * Creates a unary operator expression.
     */
    public static <T>CtUnaryOperator<T> createUnaryOperator() {
        return factory.Core().createUnaryOperator();
    }

    /** 
     * Creates a variable access.
     */
    public static <T>CtVariableAccess<T> createVariableRead(CtVariableReference<T> variable, boolean isStatic) {
        return factory.Code().createVariableRead(variable, isStatic);
    }

    /** 
     * Creates a variable read expression.
     */
    public static <T>CtVariableRead<T> createVariableRead() {
        return factory.Core().createVariableRead();
    }

    /** 
     * Creates a variable write expression.
     */
    public static <T>CtVariableWrite<T> createVariableWrite() {
        return factory.Core().createVariableWrite();
    }

    /** 
     * Creates a class.
     */
    public static <T>CtClass<T> createClass() {
        return factory.Core().createClass();
    }

    /** 
     * Creates a class from its qualified name.
     * 
     *  @param <T>
     *             type of created class
     *  @param qualifiedName
     *             full name of class to create. Name can contain . or $ for
     *             inner types
     */
    public static <T>CtClass<T> createClass(String qualifiedName) {
        return factory.Class().create(qualifiedName);
    }

    /** 
     * Creates an inner class.
     * 
     *  @param declaringClass
     *             declaring class
     *  @param simpleName
     *             simple name of inner class (without . or $)
     */
    public static <T>CtClass<T> createClass(CtClass<?> declaringClass, String simpleName) {
        return factory.Class().create(declaringClass, simpleName);
    }

    /** 
     * Creates a top-level class.
     * 
     *  @param owner
     *             the declaring package
     *  @param simpleName
     *             the simple name
     */
    public static <T>CtClass<T> createClass(CtPackage owner, String simpleName) {
        return factory.Class().create(owner, simpleName);
    }

    /** 
     * Gets a class from its runtime Java class.
     * 
     *  @param <T>
     *             type of created class
     *  @param cl
     *             the java class: note that this class should be Class&lt;T&gt; but
     *             it then poses problem when T is a generic type itself
     */
    public static <T>CtClass<T> getClass(Class<?> cl) {
        return factory.Class().get(cl);
    }

    /** 
     * Searches for a class from his qualified name.
     * 
     *  @param <T>
     *             the type of the class
     *  @param qualifiedName
     *             to search
     *  @return found class or null
     */
    public static <T>CtClass<T> getClass(String qualifiedName) {
        return factory.Class().get(qualifiedName);
    }

    /** 
     * Creates a constructor.
     */
    public static <T>CtConstructor<T> createConstructor() {
        return factory.Core().createConstructor();
    }

    /** 
     * Creates an empty constructor.
     * 
     *  @param modifiers
     *  		the modifiers
     *  @param parameters
     *  		the parameters
     *  @param thrownTypes
     *  		the thrown types
     */
    public static <T>CtConstructor<T> createConstructor(CtClass<T> target, Set<ModifierKind> modifiers, List<CtParameter<?>> parameters, Set<CtTypeReference<? extends Throwable>> thrownTypes) {
        return factory.Constructor().create(target, modifiers, parameters, thrownTypes);
    }

    /** 
     * Creates a constructor.
     * 
     *  @param modifiers
     *  		the modifiers
     *  @param parameters
     *  		the parameters
     *  @param thrownTypes
     *  		the thrown types
     *  @param body
     *  		the body
     */
    public static <T>CtConstructor<T> createConstructor(CtClass<T> target, Set<ModifierKind> modifiers, List<CtParameter<?>> parameters, Set<CtTypeReference<? extends Throwable>> thrownTypes, CtBlock<T> body) {
        return factory.Constructor().create(target, modifiers, parameters, thrownTypes, body);
    }

    /** 
     * Copies a constructor into a target class.
     * 
     *  @param target
     *  		the target class
     *  @param source
     *  		the constructor to be copied
     *  @return the new constructor
     */
    public static <T>CtConstructor<T> createConstructor(CtClass<T> target, CtConstructor<?> source) {
        return factory.Constructor().create(target, source);
    }

    /** 
     * Creates a constructor into a target class by copying it from a source
     *  method.
     * 
     *  @param target
     *  		the target class
     *  @param source
     *  		the method to be copied
     *  @return the new constructor
     */
    public static <T>CtConstructor<T> createConstructor(CtClass<T> target, CtMethod<?> source) {
        return factory.Constructor().create(target, source);
    }

    /** 
     * Create the default empty constructor.
     * 
     *  @param target
     *  		the class to insert the constructor into
     *  @return the created constructor
     */
    public static <T>CtConstructor<T> createDefault(CtClass<T> target) {
        return factory.Constructor().createDefault(target);
    }

    /** 
     * Creates a field.
     * 
     *  @param name
     *  		Name of the field.
     *  @param type
     *  		Type of the field.
     *  @param exp
     *  		Default expression of the field.
     *  @param visibilities
     *  		All visibilities of the field.
     *  @param <T>
     *  		Generic type for the type of the field.
     *  @return a field
     */
    public static <T>CtField<T> createCtField(String name, CtTypeReference<T> type, String exp, ModifierKind... visibilities) {
        return factory.Code().createCtField(name, type, exp, visibilities);
    }

    /** 
     * Creates a field.
     */
    public static <T>CtField<T> createField() {
        return factory.Core().createField();
    }

    /** 
     * Creates a field.
     * 
     *  @param target
     *  		the target type to which the field is added
     *  @param modifiers
     *  		the modifiers
     *  @param type
     *  		the field's type
     *  @param name
     *  		the field's name
     */
    public static <T>CtField<T> createField(CtType<?> target, Set<ModifierKind> modifiers, CtTypeReference<T> type, String name) {
        return factory.Field().create(target, modifiers, type, name);
    }

    /** 
     * Creates a field.
     * 
     *  @param target
     *  		the target type to which the field is added
     *  @param modifiers
     *  		the modifiers
     *  @param type
     *  		the field's type
     *  @param name
     *  		the field's name
     *  @param defaultExpression
     *  		the initializing expression
     */
    public static <T>CtField<T> createField(CtType<?> target, Set<ModifierKind> modifiers, CtTypeReference<T> type, String name, CtExpression<T> defaultExpression) {
        return factory.Field().create(target, modifiers, type, name, defaultExpression);
    }

    /** 
     * Creates a field by copying an existing field.
     * 
     *  @param <T>
     *  		the type of the field
     *  @param target
     *  		the target type where the new field has to be inserted to
     *  @param source
     *  		the source field to be copied
     *  @return the newly created field
     */
    public static <T>CtField<T> createField(CtType<?> target, CtField<T> source) {
        return factory.Field().create(target, source);
    }

    /** 
     * Creates an interface.
     */
    public static <T>CtInterface<T> createInterface() {
        return factory.Core().createInterface();
    }

    /** 
     * Creates an interface.
     */
    public static <T>CtInterface<T> createInterface(String qualifiedName) {
        return factory.Interface().create(qualifiedName);
    }

    /** 
     * Creates an interface.
     */
    public static <T>CtInterface<T> createInterface(CtPackage owner, String simpleName) {
        return factory.Interface().create(owner, simpleName);
    }

    /** 
     * Creates an inner interface
     */
    public static <T>CtInterface<T> createInterface(CtType<T> owner, String simpleName) {
        return factory.Interface().create(owner, simpleName);
    }

    /** 
     * Gets a interface from its runtime Java class.
     * 
     *  @param <T>
     *             type of created class
     *  @param cl
     *             the java class: note that this class should be Class&lt;T&gt; but
     *             it then poses problem when T is a generic type itself
     */
    public static <T>CtInterface<T> getInterface(Class<?> cl) {
        return factory.Interface().get(cl);
    }

    /** 
     * Gets a created interface
     * 
     *  @return the interface or null if does not exist
     */
    public static <T>CtInterface<T> getInterface(String qualifiedName) {
        return factory.Interface().get(qualifiedName);
    }

    /** 
     * Creates a method.
     */
    public static <T>CtMethod<T> createMethod() {
        return factory.Core().createMethod();
    }

    /** 
     * Creates an empty method.
     * 
     *  @param target
     *  		the class where the method is inserted
     *  @param modifiers
     *  		the modifiers
     *  @param returnType
     *  		the method's return type
     *  @param name
     *  		the method's name
     *  @param parameters
     *  		the parameters
     *  @param thrownTypes
     *  		the thrown types
     */
    public static <T>CtMethod<T> createMethod(CtType<?> target, Set<ModifierKind> modifiers, CtTypeReference<T> returnType, String name, List<CtParameter<?>> parameters, Set<CtTypeReference<? extends Throwable>> thrownTypes) {
        return factory.Method().create(target, modifiers, returnType, name, parameters, thrownTypes);
    }

    /** 
     * Creates a method by copying an existing method.
     * 
     *  @param <T>
     *  		the type of the method
     *  @param target
     *  		the target type where the new method has to be inserted to
     *  @param source
     *  		the source method to be copied
     *  @param redirectReferences
     *  		tells if all the references to the owning type of the source
     *  		method should be redirected to the target type (true is
     *  		recommended for most uses)
     *  @return the newly created method
     */
    public static <T>CtMethod<T> createMethod(CtType<?> target, CtMethod<T> source, boolean redirectReferences) {
        return factory.Method().create(target, source, redirectReferences);
    }

    /** 
     * Creates a parameter.
     */
    public static <T>CtParameter<T> createParameter() {
        return factory.Core().createParameter();
    }

    /** 
     * Creates a new parameter.
     */
    public static <T>CtParameter<T> createParameter(CtExecutable<?> parent, CtTypeReference<T> type, String name) {
        return factory.Executable().createParameter(parent, type, name);
    }

    /** 
     * Gets a type from its runtime Java class.
     * 
     *  @param <T>
     *  		actual type of the class
     *  @param cl
     *  		the java class: note that this class should be Class&lt;T&gt; but it
     *  		then poses problem when T is a generic type itself
     */
    public static <T>CtType<T> getType(Class<?> cl) {
        return factory.Type().get(cl);
    }

    /** 
     * Gets a created type from its qualified name.
     * 
     *  @return a found type or null if does not exist
     */
    public static <T>CtType<T> getType(final String qualifiedName) {
        return factory.Type().get(qualifiedName);
    }

    /** 
     * Creates an implicit array type reference.
     */
    public static <T>CtImplicitArrayTypeReference<T> createImplicitArrayTypeReference() {
        return factory.Internal().createImplicitArrayTypeReference();
    }

    /** 
     * Creates a inference type reference.
     */
    public static <T>CtImplicitTypeReference<T> createImplicitTypeReference() {
        return factory.Internal().createImplicitTypeReference();
    }

    /** 
     * Creates a reference to an array of given type.
     */
    public static <T>CtArrayTypeReference<T> createArrayReference(String qualifiedName) {
        return factory.Type().createArrayReference(qualifiedName);
    }

    /** 
     * Creates a reference to an array of given type.
     * 
     *  @param <T>
     *  		type of array
     *  @param type
     *  		type of array values
     */
    public static <T>CtArrayTypeReference<T[]> createArrayReference(CtType<T> type) {
        return factory.Type().createArrayReference(type);
    }

    /** 
     * Creates a reference to a one-dimension array of given type.
     */
    public static <T>CtArrayTypeReference<T[]> createArrayReference(CtTypeReference<T> reference) {
        return factory.Type().createArrayReference(reference);
    }

    /** 
     * Creates an array type reference.
     */
    public static <T>CtArrayTypeReference<T> createArrayTypeReference() {
        return factory.Core().createArrayTypeReference();
    }

    /** 
     * Creates a catch variable reference.
     */
    public static <T>CtCatchVariableReference<T> createCatchVariableReference() {
        return factory.Core().createCatchVariableReference();
    }

    /** 
     * Creates a catch variable reference that points to an existing catch
     *  variable (strong referencing).
     */
    public static <T>CtCatchVariableReference<T> createCatchVariableReference(CtCatchVariable<T> catchVariable) {
        return factory.Code().createCatchVariableReference(catchVariable);
    }

    /** 
     * Creates an executable reference.
     */
    public static <T>CtExecutableReference<T> createExecutableReference() {
        return factory.Core().createExecutableReference();
    }

    /** 
     * Creates a constructor reference from an actual constructor.
     */
    public static <T>CtExecutableReference<T> createReferenceConstructor(Constructor<T> constructor) {
        return factory.Constructor().createReference(constructor);
    }

    /** 
     * Creates a constructor reference from an existing constructor.
     */
    public static <T>CtExecutableReference<T> createReferenceConstructor(CtConstructor<T> c) {
        return factory.Constructor().createReference(c);
    }

    /** 
     * Creates an executable reference from its signature, as defined by the
     *  executable reference's toString.
     */
    public static <T>CtExecutableReference<T> createReferenceExecutable(String signature) {
        return factory.Executable().createReference(signature);
    }

    /** 
     * Creates an executable reference from an existing executable.
     */
    public static <T>CtExecutableReference<T> createReferenceExecutable(CtExecutable<T> e) {
        return factory.Executable().createReference(e);
    }

    /** 
     * Creates an executable reference.
     * 
     *  @param declaringType
     *  		reference to the declaring type
     *  @param isStatic
     *  		if this reference references a static executable
     *  @param type
     *  		the return type of the executable
     *  @param methodName
     *  		simple name
     *  @param parameterTypes
     *  		list of parameter's types
     */
    public static <T>CtExecutableReference<T> createReferenceExecutable(CtTypeReference<?> declaringType, boolean isStatic, CtTypeReference<T> type, String methodName, List<CtTypeReference<?>> parameterTypes) {
        return factory.Executable().createReference(declaringType, isStatic, type, methodName, parameterTypes);
    }

    /** 
     * Creates an executable reference.
     * 
     *  @param declaringType
     *  		reference to the declaring type
     *  @param isStatic
     *  		if this reference references a static executable
     *  @param type
     *  		the return type of the executable
     *  @param methodName
     *  		simple name
     *  @param parameterTypes
     *  		list of parameter's types
     */
    public static <T>CtExecutableReference<T> createReferenceExecutable(CtTypeReference<?> declaringType, boolean isStatic, CtTypeReference<T> type, String methodName, CtTypeReference<?>... parameterTypes) {
        return factory.Executable().createReference(declaringType, isStatic, type, methodName, parameterTypes);
    }

    /** 
     * Creates an executable reference.
     * 
     *  @param declaringType
     *  		reference to the declaring type
     *  @param type
     *  		the return type of the executable
     *  @param methodName
     *  		simple name
     *  @param parameterTypes
     *  		list of parameter's types
     */
    public static <T>CtExecutableReference<T> createReferenceExecutable(CtTypeReference<?> declaringType, CtTypeReference<T> type, String methodName, List<CtTypeReference<?>> parameterTypes) {
        return factory.Executable().createReference(declaringType, type, methodName, parameterTypes);
    }

    /** 
     * Creates an executable reference.
     * 
     *  @param declaringType
     *  		reference to the declaring type
     *  @param type
     *  		the executable's type
     *  @param methodName
     *  		simple name
     *  @param parameterTypes
     *  		list of parameter's types
     */
    public static <T>CtExecutableReference<T> createReferenceExecutable(CtTypeReference<?> declaringType, CtTypeReference<T> type, String methodName, CtTypeReference<?>... parameterTypes) {
        return factory.Executable().createReference(declaringType, type, methodName, parameterTypes);
    }

    /** 
     * Creates a method reference from an actual method.
     */
    public static <T>CtExecutableReference<T> createReferenceMethod(Method method) {
        return factory.Method().createReference(method);
    }

    /** 
     * Creates a method reference.
     */
    public static <T>CtExecutableReference<T> createReferenceMethod(CtMethod<T> m) {
        return factory.Method().createReference(m);
    }

    /** 
     * Creates a field reference.
     */
    public static <T>CtFieldReference<T> createFieldReference() {
        return factory.Core().createFieldReference();
    }

    /** 
     * Creates a field reference from its signature, as defined by the field
     *  reference's toString.
     */
    public static <T>CtFieldReference<T> createReferenceField(String signature) {
        return factory.Field().createReference(signature);
    }

    /** 
     * Creates a field reference from a <code>java.lang.reflect</code> field.
     */
    public static <T>CtFieldReference<T> createReferenceField(Field field) {
        return factory.Field().createReference(field);
    }

    /** 
     * Creates a field reference from an existing field.
     */
    public static <T>CtFieldReference<T> createReferenceField(CtField<T> field) {
        return factory.Field().createReference(field);
    }

    /** 
     * Creates a field reference.
     */
    public static <T>CtFieldReference<T> createReferenceField(CtTypeReference<?> declaringType, CtTypeReference<T> type, String fieldName) {
        return factory.Field().createReference(declaringType, type, fieldName);
    }

    /** 
     * Creates a local variable reference.
     */
    public static <T>CtLocalVariableReference<T> createLocalVariableReference() {
        return factory.Core().createLocalVariableReference();
    }

    /** 
     * Creates a local variable reference that points to an existing local
     *  variable (strong referencing).
     */
    public static <T>CtLocalVariableReference<T> createLocalVariableReference(CtLocalVariable<T> localVariable) {
        return factory.Code().createLocalVariableReference(localVariable);
    }

    /** 
     * Creates a local variable reference with its name an type (weak
     *  referencing).
     */
    public static <T>CtLocalVariableReference<T> createLocalVariableReference(CtTypeReference<T> type, String name) {
        return factory.Code().createLocalVariableReference(type, name);
    }

    /** 
     * Creates a parameter reference.
     */
    public static <T>CtParameterReference<T> createParameterReference() {
        return factory.Core().createParameterReference();
    }

    /** 
     * Creates a parameter reference from an existing parameter.
     * 
     *  @param <T>
     *  		the parameter's type
     *  @param parameter
     *  		the parameter
     */
    public static <T>CtParameterReference<T> createParameterReference(CtParameter<T> parameter) {
        return factory.Executable().createParameterReference(parameter);
    }

    /** 
     * Creates a type reference.
     * 
     *  @param originalClass
     *  		Original class of the reference.
     *  @param <T>
     *  		Type of the reference.
     *  @return a type reference.
     */
    public static <T>CtTypeReference<T> createCtTypeReference(Class<?> originalClass) {
        return factory.Code().createCtTypeReference(originalClass);
    }

    /** 
     * Creates a reference to a simple type
     */
    public static <T>CtTypeReference<T> createReferenceType(Class<T> type) {
        return factory.Type().createReference(type);
    }

    /** 
     * Create a reference to a simple type
     */
    public static <T>CtTypeReference<T> createReferenceType(String qualifiedName) {
        return factory.Type().createReference(qualifiedName);
    }

    /** 
     * Create a reference to a simple type
     */
    public static <T>CtTypeReference<T> createReferenceType(CtType<T> type) {
        return factory.Type().createReference(type);
    }

    /** 
     * Creates a type reference.
     */
    public static <T>CtTypeReference<T> createTypeReference() {
        return factory.Core().createTypeReference();
    }

    /** 
     * Gets the list of all created packages. It includes all the top-level
     *  packages and their sub-packages.
     */
    public static Collection<CtPackage> getAllPackage() {
        return factory.Package().getAll();
    }

    /** 
     * Gets all the main methods stored in this factory.
     */
    public static Collection<CtMethod<Void>> getMainMethods() {
        return factory.Method().getMainMethods();
    }

    /** 
     * Creates a collection of type references from a collection of classes.
     */
    public static List<CtTypeReference<?>> createReferences(List<Class<?>> classes) {
        return factory.Type().createReferences(classes);
    }

    /** 
     * Creates a list of variable accesses.
     * 
     *  @param variables
     *  		the variables to be accessed
     */
    public static List<CtExpression<?>> createVariableReads(List<? extends CtVariable<?>> variables) {
        return factory.Code().createVariableReads(variables);
    }

    /** 
     * Gets the list of all top-level created types.
     */
    public static List<CtType<?>> getAllType() {
        return factory.Type().getAll();
    }

    /** 
     * Gets the list of all created types.
     */
    public static List<CtType<?>> getAllType(boolean includeNestedTypes) {
        return factory.Type().getAll(includeNestedTypes);
    }

    /** 
     * Gets the compilation unit map.
     * 
     *  @return a map (path -&gt; {@link CompilationUnit})
     */
    public static Map<String, CompilationUnit> getMap() {
        return factory.CompilationUnit().getMap();
    }

    /** 
     * Creates a modifier set.
     * 
     *  @param modifiers
     *  		to put in set
     *  @return Set of given modifiers
     */
    public static Set<ModifierKind> modifiers(ModifierKind... modifiers) {
        return factory.Code().modifiers(modifiers);
    }

    /** 
     * Creates a <code>break</code> statement.
     */
    public static CtBreak createBreak() {
        return factory.Core().createBreak();
    }

    /** 
     * Creates a <code>catch</code> clause.
     */
    public static CtCatch createCatch() {
        return factory.Core().createCatch();
    }

    /** 
     * Creates a catch element.
     * 
     *  @param nameCatch
     *  		Name of the variable in the catch.
     *  @param exception
     *  		Type of the exception.
     *  @param ctBlock
     *  		Content of the catch.
     *  @return a catch.
     */
    public static CtCatch createCtCatch(String nameCatch, Class<? extends Throwable> exception, CtBlock<?> ctBlock) {
        return factory.Code().createCtCatch(nameCatch, exception, ctBlock);
    }

    /** 
     * Creates a code snippet statement.
     */
    public static CtCodeSnippetStatement createCodeSnippetStatement() {
        return factory.Core().createCodeSnippetStatement();
    }

    /** 
     * Creates a Code Snippet statement.
     * 
     *  @param statement
     *  		The String containing the statement.
     *  @return a new CtCodeSnippetStatement
     */
    public static CtCodeSnippetStatement createCodeSnippetStatement(String statement) {
        return factory.Code().createCodeSnippetStatement(statement);
    }

    /** 
     * Creates a <code>continue</code> statement.
     */
    public static CtContinue createContinue() {
        return factory.Core().createContinue();
    }

    /** 
     * Creates a <code>do</code> loop.
     */
    public static CtDo createDo() {
        return factory.Core().createDo();
    }

    /** 
     * Creates a <code>for</code> loop.
     */
    public static CtFor createFor() {
        return factory.Core().createFor();
    }

    /** 
     * Creates a <code>foreach</code> loop.
     */
    public static CtForEach createForEach() {
        return factory.Core().createForEach();
    }

    /** 
     * Creates an <code>if</code> statement.
     */
    public static CtIf createIf() {
        return factory.Core().createIf();
    }

    /** 
     * Creates a <code>synchronized</code> statement.
     */
    public static CtSynchronized createSynchronized() {
        return factory.Core().createSynchronized();
    }

    /** 
     * Creates a throw.
     * 
     *  @param thrownExp
     *  		Expression of the throw.
     *  @return a throw.
     */
    public static CtThrow createCtThrow(String thrownExp) {
        return factory.Code().createCtThrow(thrownExp);
    }

    /** 
     * Creates a <code>throw</code> statement.
     */
    public static CtThrow createThrow() {
        return factory.Core().createThrow();
    }

    /** 
     * Creates a <code>try</code> block.
     */
    public static CtTry createTry() {
        return factory.Core().createTry();
    }

    /** 
     * Creates a <code>try</code> with resource block.
     */
    public static CtTryWithResource createTryWithResource() {
        return factory.Core().createTryWithResource();
    }

    /** 
     * Creates a <code>while</code> loop.
     */
    public static CtWhile createWhile() {
        return factory.Core().createWhile();
    }

    /** 
     * Creates a compilation unit with no associated files.
     */
    public static CompilationUnit createCompilationUnit() {
        return factory.CompilationUnit().create();
    }

    /** 
     * Creates or gets a compilation unit for a given file path.
     */
    public static CompilationUnit createCompilationUnit(String filePath) {
        return factory.CompilationUnit().create(filePath);
    }

    /** 
     * Creates a virtual compilation unit.
     */
    public static CompilationUnit createVirtualCompilationUnit() {
        return factory.Core().createVirtualCompilationUnit();
    }

    /** 
     * Creates an import for the given type.
     */
    public static Import createImport(Class<?> type) {
        return factory.CompilationUnit().createImport(type);
    }

    /** 
     * Creates an import for the given field.
     */
    public static Import createImport(CtFieldReference<?> field) {
        return factory.CompilationUnit().createImport(field);
    }

    /** 
     * Creates an import for the given package.
     */
    public static Import createImport(CtPackageReference pack) {
        return factory.CompilationUnit().createImport(pack);
    }

    /** 
     * Creates an import for the given type.
     */
    public static Import createImport(CtTypeReference<?> type) {
        return factory.CompilationUnit().createImport(type);
    }

    /** 
     * Creates a source position.
     */
    public static SourcePosition createSourcePosition(CompilationUnit compilationUnit, int startDeclaration, int startSource, int end, int[] lineSeparatorPositions) {
        return factory.Core().createSourcePosition(compilationUnit, startDeclaration, startSource, end, lineSeparatorPositions);
    }

    /** 
     * Creates an annotation type.
     * 
     *  @param qualifiedName
     *  		the fully qualified name of the annotation type.
     */
    public static CtAnnotationType<?> createAnnotation(String qualifiedName) {
        return factory.Annotation().create(qualifiedName);
    }

    /** 
     * Creates an anonymous executable (initializer block) in a target class).
     */
    public static CtAnonymousExecutable createAnonymous(CtClass<?> target, CtBlock<Void> body) {
        return factory.Executable().createAnonymous(target, body);
    }

    /** 
     * Creates an anonymous executable.
     */
    public static CtAnonymousExecutable createAnonymousExecutable() {
        return factory.Core().createAnonymousExecutable();
    }

    /** 
     * Creates an enum from its qualified name.
     */
    public static CtEnum<?> createEnum(String qualifiedName) {
        return factory.Enum().create(qualifiedName);
    }

    /** 
     * Creates a new enumeration type
     * 
     *  @param owner
     *  		package
     *  @param simpleName
     *  		the simple name
     */
    public static CtEnum<?> createEnum(CtPackage owner, String simpleName) {
        return factory.Enum().create(owner, simpleName);
    }

    /** 
     * Gets an already created enumeration from its qualified name.
     * 
     *  @return the enumeration or null if does not exist
     */
    public static CtEnum<?> getEnum(String qualifiedName) {
        return factory.Enum().get(qualifiedName);
    }

    /** 
     * Creates a package.
     */
    public static CtPackage createPackage() {
        return factory.Core().createPackage();
    }

    /** 
     * Creates a new package (see also {@link #getOrCreate(String)}).
     * 
     *  @param parent
     *  		the parent package (can be null)
     *  @param simpleName
     *  		the package's simple name (no dots)
     *  @return the newly created package
     */
    public static CtPackage createPackage(CtPackage parent, String simpleName) {
        return factory.Package().create(parent, simpleName);
    }

    /** 
     * Gets or creates a package.
     * 
     *  @param qualifiedName
     *  		the full name of the package
     */
    public static CtPackage getOrCreate(String qualifiedName) {
        return factory.Package().getOrCreate(qualifiedName);
    }

    /** 
     * Gets a created package.
     * 
     *  @param qualifiedName
     *  		the package to search
     *  @return a found package or null
     */
    public static CtPackage getPackage(String qualifiedName) {
        return factory.Package().get(qualifiedName);
    }

    /** 
     * Return the unnamed top-level package.
     */
    public static CtPackage getRootPackage() {
        return factory.Package().getRootPackage();
    }

    /** 
     * Creates a type parameter.
     */
    public static CtTypeParameter createTypeParameter() {
        return factory.Core().createTypeParameter();
    }

    /** 
     * Creates a type parameter with no bounds.
     * 
     *  @param owner
     *  		the owning declaration
     *  @param name
     *  		the name of the formal parameter
     */
    public static CtTypeParameter createTypeParameter(CtElement owner, String name) {
        return factory.Type().createTypeParameter(owner, name);
    }

    /** 
     * Creates a type parameter.
     * 
     *  @param owner
     *  		the owning declaration
     *  @param name
     *  		the name of the formal parameter
     *  @param bounds
     *  		the bounds
     */
    public static CtTypeParameter createTypeParameter(CtElement owner, String name, List<CtTypeReference<?>> bounds) {
        return factory.Type().createTypeParameter(owner, name, bounds);
    }

    /** 
     * Creates a partial evaluator on the Spoon meta-model.
     */
    public static PartialEvaluator createPartialEvaluator() {
        return factory.Eval().createPartialEvaluator();
    }

    /** 
     * Gets the main factory of that core factory (cannot be <code>null</code>).
     */
    public static Factory getMainFactory() {
        return factory.Core().getMainFactory();
    }

    /** 
     * Creates a circular type reference.
     */
    public static CtCircularTypeReference createCircularTypeReference() {
        return factory.Internal().createCircularTypeReference();
    }

    /** 
     * Creates a reference to an n-dimension array of given type.
     */
    public static CtArrayTypeReference<?> createArrayReference(CtTypeReference<?> reference, int n) {
        return factory.Type().createArrayReference(reference, n);
    }

    /** 
     * Creates a package reference.
     * 
     *  @param originalPackage
     *  		Original package of the reference.
     *  @return a package reference.
     */
    public static CtPackageReference createCtPackageReference(Package originalPackage) {
        return factory.Code().createCtPackageReference(originalPackage);
    }

    /** 
     * Creates a package reference.
     */
    public static CtPackageReference createPackageReference() {
        return factory.Core().createPackageReference();
    }

    /** 
     * Creates a reference to a package by using its Java runtime
     *  representation.
     * 
     *  @param pack
     *  		a runtime package
     *  @return reference to the package
     */
    public static CtPackageReference createReferencePackage(Package pack) {
        return factory.Package().createReference(pack);
    }

    /** 
     * Creates a reference to a package.
     * 
     *  @param name
     *  		full name of the package to reference
     */
    public static CtPackageReference createReferencePackage(String name) {
        return factory.Package().createReference(name);
    }

    /** 
     * Creates a reference to an existing package.
     */
    public static CtPackageReference createReferencePackage(CtPackage pack) {
        return factory.Package().createReference(pack);
    }

    /** 
     * Returns a reference on the top level package.
     */
    public static CtPackageReference topLevel() {
        return factory.Package().topLevel();
    }

    /** 
     * Creates a type parameter reference.
     */
    public static CtTypeParameterReference createTypeParameterReference() {
        return factory.Core().createTypeParameterReference();
    }

    /** 
     * Creates a type parameter reference with no bounds.
     * 
     *  @param name
     *  		the name of the formal parameter
     */
    public static CtTypeParameterReference createTypeParameterReference(String name) {
        return factory.Type().createTypeParameterReference(name);
    }

    /** 
     * Creates a type parameter reference.
     * 
     *  @param name
     *  		the name of the formal parameter
     *  @param bounds
     *  		the bounds
     */
    public static CtTypeParameterReference createTypeParameterReference(String name, List<CtTypeReference<?>> bounds) {
        return factory.Type().createTypeParameterReference(name, bounds);
    }

    /** 
     * Returns a reference on the null type (type of null).
     */
    public static CtTypeReference<?> nullType() {
        return factory.Type().nullType();
    }
}

