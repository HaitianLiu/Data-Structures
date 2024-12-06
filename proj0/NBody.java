public class NBody{
  public static double readRadius(String fileName){
      In in = new In(fileName);
      int numOfPlanets = in.readInt();
      double radius = in.readDouble();
      return radius;
  }


  public static Body[] readBodies(String fileName){
      In in = new In(fileName);
      int numOfPlanets = in.readInt();
      double radius = in.readDouble();
      Body[] bodies = new Body[numOfPlanets];
      int i=0;
      while(i < numOfPlanets){
         double xxPos = in.readDouble();
         double yyPos =in.readDouble();
         double xxVel = in.readDouble();
         double yyVel = in.readDouble();
         double mass = in.readDouble();
         String image = in.readString();

         bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, image);
         i += 1;
      }
      return bodies;
  }


   public static void main(String[] args) {
       double T = Double.parseDouble(args[0]);
       double dt = Double.parseDouble(args[1]);
       String filename = args[2];
       Body[] bodies = NBody.readBodies(filename);
       Double radius =  NBody.readRadius(filename);

       StdDraw.enableDoubleBuffering();
       StdDraw.setScale(-radius, radius);
       StdDraw.clear();
       StdDraw.picture(0,0, "images/starfield.jpg");
       StdDraw.show();
       for(Body b: bodies){
         b.draw();
       }


       for(double t=0; t <= T; t+=dt){
         double xForces[] = new double[bodies.length];
         double yForces[] = new double[bodies.length];
         for (int i=0; i < bodies.length; i+=1 ){
             xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
             yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
         }

         for (int j=0 ; j < bodies.length; j += 1){
             bodies[j].update(dt,xForces[j],yForces[j]);
         }

         StdDraw.picture(0, 0, "images/starfield.jpg");

         for(Body b: bodies){
           b.draw();
         }

         StdDraw.pause(10);

       }

       StdOut.printf("%d\n", bodies.length);
       StdOut.printf("%.2e\n", radius);
       for (int i = 0; i < bodies.length; i++) {
              StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                         bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                         bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
               }


   }
}
