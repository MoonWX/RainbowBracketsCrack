package com.starxg.janetfilter.plugin.rainbowbrackets;

import com.janetfilter.core.plugin.MyTransformer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.ProtectionDomain;
import java.util.Arrays;


public class HideJaNetfilterTransformer implements MyTransformer {


    @Override
    public String getHookClassName() {
        return new String(new byte[]{-29, -125, -101, -31, -70, -115},
                StandardCharsets.UTF_8);
    }


    @Override
    public byte[] transform(ClassLoader loader, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, String className, byte[] classBytes, int order) throws Exception {
        final ClassPool pool = ClassPool.getDefault();
        pool.appendSystemPath();
        final CtClass clazz = pool.makeClass(new ByteArrayInputStream(classBytes));
        clazz.getDeclaredMethod(new String(new byte[]{-50, -84, -50, -119}, StandardCharsets.UTF_8)).insertBefore("if(true)return false;");
        clazz.getDeclaredMethod(new String(new byte[]{-32, -71, -128, -29, -125, -109}, StandardCharsets.UTF_8)).insertBefore("if(true)return false;");
        clazz.getDeclaredMethod(new String(new byte[]{-32, -82, -119, -31, -126, -67}, StandardCharsets.UTF_8)).insertBefore("if(true)return false;");
        clazz.getDeclaredMethod(new String(new byte[]{-50, -110, -50, -100}, StandardCharsets.UTF_8)).insertBefore("if(true)return false;");
        clazz.getDeclaredMethod(new String(new byte[]{-29, -127, -69, -31, -125, -82}, StandardCharsets.UTF_8)).insertBefore("if(true)return false;");
        clazz.getDeclaredMethod(new String(new byte[]{-31, -68, -94, -32, -92, -112}, StandardCharsets.UTF_8)).insertBefore("if(true)return false;");
        clazz.getDeclaredMethod(new String(new byte[]{-32, -76, -121, -31, -67, -91}, StandardCharsets.UTF_8)).insertBefore("if(true)return false;");

        classBytes = clazz.toBytecode();
        clazz.detach();

        return classBytes;
    }

}
