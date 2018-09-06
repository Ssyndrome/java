package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.MethodWithAnnotation;
import com.cultivation.javaBasic.util.MyAnnotation;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {
    @Test
    void should_be_able_to_get_class_object() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final Class<? extends Employee> expected = Employee.class;
        // --end-->

        assertEquals(expected, employeeClass);
    }

    @Test
    void should_be_able_to_get_full_qualified_name() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = Employee.class.getName();
        // --end-->

        assertEquals(expected, employeeClass.getName());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_instantiate_types_at_runtime() throws Exception {
        Class<?> theClass = Class.forName("com.cultivation.javaBasic.util.Employee");

        // TODO: please created an instance described by `theClass`
        // <--start
        Employee instance = (Employee) theClass.newInstance();
        // --end-->

        assertEquals("Employee", instance.getTitle());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_print_all_static_methods_of_double() {
        Class<Double> doubleClass = Double.class;

        // TODO: please get all public static declared methods of Double. Sorted in an ascending order
        // <--start
        List<String> methodList = Arrays.stream(doubleClass.getDeclaredMethods()).filter(i-> Modifier.isStatic(i.getModifiers())).map(Method::getName).sorted().collect(Collectors.toList());
        String[] publicStaticMethods = new String[methodList.size()];
        for (int i = 0; i < methodList.size(); i++) {
            publicStaticMethods[i] = methodList.get(i);
        }
        // --end-->

        final String[] expected = {
            "compare", "doubleToLongBits", "doubleToRawLongBits", "hashCode",
            "isFinite", "isInfinite", "isNaN", "longBitsToDouble", "max",
            "min", "parseDouble", "sum", "toHexString", "toString", "valueOf",
            "valueOf"
        };

        assertArrayEquals(expected, publicStaticMethods);
    }

    @SuppressWarnings({"unused", "ConstantConditions", "RedundantThrows"})
    @Test
    void should_be_able_to_evaluate_object_field_values_at_runtime() throws Exception {
        Employee employee = new Employee();

        // TODO: please get the value of `getTitle` method using reflection. No casting to Employee is allowed.
        // <--start
        Object result = employee.getClass().getDeclaredMethod("getTitle").invoke(employee);
        // --end-->

        assertEquals("Employee", result);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_be_able_to_get_the_item_class_of_the_array() {
        Object employees = new Employee[0];

        // TODO: please get the class of array item `employees`
        // <--start
        Class<?> itemClass = employees.getClass().getComponentType();
        // --end-->

        assertEquals(Employee.class, itemClass);
    }


    @Test
    void should_get_method_with_annotation() {
        TestAnnotationMethod testAnnotationMethod = new TestAnnotationMethod();

        Method[] allMethods = testAnnotationMethod.getClass().getDeclaredMethods();
        List<String> allAnnotatedMethods = Arrays.stream(allMethods).filter(i->i.isAnnotationPresent(SurpriseAnnotation.class)).map(i->i.getName()).collect(Collectors.toList());

        String[] result = new String[allAnnotatedMethods.size()];
        for (int i = 0; i < allAnnotatedMethods.size(); i++) {
            result[i] = allAnnotatedMethods.get(i);
        }

        String[] expectedAnnotatedMethods = new String[]{"toTestSurpriseAnnotation", "toTestSecondMethodAnnotation"};
        assertEquals(expectedAnnotatedMethods[1], result[1]);
    }

    @Test
    void should_not_equal_when_have_parent_type_array() {
        ChildrenTest[] childrenTests = new ChildrenTest[2];
        ParentTest[] parentTests = new ParentTest[4];

        Class<?> classOfChildrenArray = childrenTests.getClass().getSuperclass();
        Class<? extends ParentTest[]> classOfParentArray = parentTests.getClass();

        Class<?> classOfChildrenComponent = childrenTests.getClass().getComponentType().getSuperclass();
        Class<?> classOfParentComponent = parentTests.getClass().getComponentType();

        assertNotEquals(classOfChildrenArray, classOfParentArray);
        assertEquals(classOfChildrenComponent, classOfParentComponent);
    }
}

/*
 * - What is the class name of array type?
 * - How to compare 2 classes?
 * - What if the class does not contain a default constructor when you call `newInstance()`?
 * - What is source only annotation? Can we get source only annotations via reflection?
 */
