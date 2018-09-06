package com.cultivation.javaBasic.util;

public class DerivedFromBaseClassForOverridingCallingSuper extends BaseClassForOverriding {
    @Override
    public String getName() {
        return super.getName() + "->DerivedFromBaseClassForOverridingCallingSuper";
    }

    public String getSuperSuper() {
        return super.getClass().getSuperclass().toString();
    }
}
