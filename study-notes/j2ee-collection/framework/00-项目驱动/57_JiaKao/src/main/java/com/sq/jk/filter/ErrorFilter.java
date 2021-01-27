package com.sq.jk.filter;

import javax.servlet.*;
import java.io.IOException;

public class ErrorFilter implements Filter {
    public static final String ERROR_URI = "/handleError";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            request.setAttribute(ERROR_URI, e);
            request.getRequestDispatcher(ERROR_URI).forward(request, response);
        }
    }
}

/*
  Filter1.doFilter {
    try {
        chain.doFilter {
            Filter2.doFilter {
                chain.doFilter {
                    Filter3.doFilter {
                        chain.doFilter {
                            Controller.login
                        }
                    }
                }
            }
        }
    } catch (Exception e) {
        // 将异常提交给ErrorController
    }
  }
*/

// public class Filter1 implements Filter {
//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//         chain.doFilter(request, response) {
//             Filter2.doFilter
//         }
//     }
// }
//
//
// public class Filter2 implements Filter {
//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//         chain.doFilter(request, response);
//     }
// }
//
//
// public class Filter3 implements Filter {
//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//         chain.doFilter(request, response);
//     }
// }
