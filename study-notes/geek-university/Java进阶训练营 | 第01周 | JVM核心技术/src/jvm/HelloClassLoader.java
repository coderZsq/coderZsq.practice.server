package jvm;

import java.util.Base64;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("jvm.Hello").newInstance(); // 加载并初始化Hello类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    // @Override
    // protected Class findClass (String name) throws ClassNotFoundException {
    //
    //     String classPath = name.replace(".", "/")+".class";
    //     InputStream classInputStream = getSystemClassLoader().getResourceAsStream(classPath);
    //     try {
    //         byte[] classBytes = new byte[classInputStream.available()];
    //         classInputStream.read(classBytes);
    //         System.out.println(Base64.getEncoder().encodeToString(classBytes));
    //         Class clazz = defineClass(name, classBytes, 0, classBytes.length);
    //         resolveClass(clazz);
    //         return clazz;
    //     } catch (IOException e) {
    //         throw new ClassNotFoundException();
    //     }
    // }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "yv66vgAAADQAHwoABgARCQASABMIABQKABUAFgcAFwcAGAEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQALTGp2bS9IZWxsbzsBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABkMABoAGwEAGEhlbGxvIENsYXNzIEluaXRpYWxpemVkIQcAHAwAHQAeAQAJanZtL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAvAAEAAQAAAAUqtwABsQAAAAIACgAAAAYAAQAAAAMACwAAAAwAAQAAAAUADAANAAAACAAOAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAABQAIAAYAAQAPAAAAAgAQ";
        byte[] bytes = decode(helloBase64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}