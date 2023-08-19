package com.starxg.janetfilter.plugin.rainbowbrackets;

import com.janetfilter.core.plugin.MyTransformer;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.ByteArrayInputStream;
import java.security.ProtectionDomain;

public class LicensingFacadeTransformer implements MyTransformer {


    @Override
    public String getHookClassName() {
        return "com/intellij/ui/LicensingFacade";
    }


    @Override
    public byte[] transform(ClassLoader loader, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, String className, byte[] classBytes, int order) throws Exception {
        final ClassPool pool = ClassPool.getDefault();
        pool.appendSystemPath();
        final CtClass clazz = pool.makeClass(new ByteArrayInputStream(classBytes));


        clazz.getDeclaredMethod("getConfirmationStamp", new CtClass[]{pool.get("java.lang.String")})
                .insertBefore("if(\"PRAINBOWBRACKET\".equals($1))return \"eval:4081698416000\";");

        classBytes = clazz.toBytecode();
        clazz.detach();

        return classBytes;
    }
}
