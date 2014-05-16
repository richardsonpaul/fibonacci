package org.mypackage.fibonacci;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

@Path("/fibonacci")
public class FibonacciApplication extends Application {

  private static final int MAXIMUM_INPUT;
  static {
    int defaultMaximum = 100;
    String configuredMaximum = System.getenv("FIBONACCI_REST_MAXIMUM");
    int maximumInput;
    try {
      maximumInput = Integer.parseInt(configuredMaximum);
    } catch (Exception _) {
      maximumInput = defaultMaximum;
    }
    MAXIMUM_INPUT = maximumInput;
  }

  public static List<BigInteger> collectFibonacciValues(int n) {
    if (n < 1) {
      return Collections.emptyList();
    }
    ArrayList<BigInteger> fibonacciValues = new ArrayList<>(n);
    fibonacciValues.add(BigInteger.ZERO);
    BigInteger currentFibonacciValue = BigInteger.ONE;
    for (int currentFibonacciInput = 1; currentFibonacciInput < n; currentFibonacciInput++) {
      fibonacciValues.add(currentFibonacciValue);
      currentFibonacciValue = currentFibonacciValue.add(fibonacciValues.get(currentFibonacciInput - 1));
    }
    return fibonacciValues;
  }

  @GET
  @Path("/{n}")
  @Produces(MediaType.APPLICATION_JSON)
  public static Response getFibonacciSeries(@PathParam("n") int n) {
    Object error = validateInput(n);
    if (error != null) {
      return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).entity(error).build();
    }
    return Response.status(HttpServletResponse.SC_OK).entity(collectFibonacciValues(n)).build();
  }

  /**
   * Checks for valid range; returns an error entity if out of range, null if no error
   * @param n
   * @return Object describing error, or null
   */
  private static Object validateInput(int n) {
    if (n < 0) {
      return error("Cannot request a fibonacci value of less than zero");
    } else if (n > MAXIMUM_INPUT) {
      return error("Value " + n + " is out of range for this service; maxiumum is " + MAXIMUM_INPUT);
    }
    return null;
  }

  private static HashMap<String, String> error(String message) {
    HashMap<String, String> error = new HashMap<>();
    error.put("error", message);
    return error;
  }

  @Override
  public Set<Object> getSingletons() {
    return new HashSet<Object>(Arrays.asList(this));
  }

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);
    ServletContextHandler context = new ServletContextHandler();
    context.setContextPath("/");
    ServletHolder h = new ServletHolder(new HttpServletDispatcher());
    h.setInitParameter("javax.ws.rs.Application", "org.mypackage.fibonacci.FibonacciApplication");
    context.addServlet(h, "/*");
    server.setHandler(context);
    server.start();
    server.join();
  }

}
