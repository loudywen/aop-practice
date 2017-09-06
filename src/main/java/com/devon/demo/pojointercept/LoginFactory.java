package com.devon.demo.pojointercept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoginFactory {

  private static class AuditInvocationHandler implements InvocationHandler {

    private final Login realLogin;

    public AuditInvocationHandler(Login realLogin) {

      this.realLogin = realLogin;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

      Method realMethod = realLogin.getClass().getMethod(
          method.getName(),
          method.getParameterTypes());
      Audit audit = realMethod.getAnnotation(Audit.class);

      if (audit != null) {
        audit.handler().newInstance().handle();
      }

      return method.invoke(realLogin, args);
    }
  }

  public static Login createLogin() {
    return (Login) Proxy.newProxyInstance(
        LoginFactory.class.getClassLoader(),
        new Class[]{Login.class},
        new AuditInvocationHandler(new LoginImpl()));
  }
}
