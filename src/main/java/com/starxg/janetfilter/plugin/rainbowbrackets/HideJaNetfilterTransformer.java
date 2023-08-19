package com.starxg.janetfilter.plugin.rainbowbrackets;

import com.janetfilter.core.plugin.MyTransformer;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.ProtectionDomain;

public class HideJaNetfilterTransformer implements MyTransformer {


    @Override
    public String getHookClassName() {
        return new String(new byte[]{-31, -66, -69, -32, -86, -75},
                StandardCharsets.UTF_8);
    }


    @Override
    public byte[] transform(ClassLoader loader, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, String className, byte[] classBytes, int order) throws Exception {
        final ClassPool pool = ClassPool.getDefault();
        pool.appendSystemPath();
        final CtClass clazz = pool.makeClass(new ByteArrayInputStream(classBytes));

        clazz.getDeclaredMethod(new String(new byte[]{-32, -82, -121, -31, -66, -72}, StandardCharsets.UTF_8))
                .insertBefore("if(true)return false;");

        classBytes = clazz.toBytecode();
        clazz.detach();

        return classBytes;
    }

}
