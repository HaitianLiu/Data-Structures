public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;


    public Body(double xP, double yP, double xV,
              double yV, double m, String img){
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
                mass =  m;
                imgFileName = img;
                 }


    public Body(Body b){
      this.xxPos = b.xxPos;
      this.yyPos = b.yyPos;
      this.xxVel = b.xxVel;
      this.yyVel = b.yyVel;
      this.mass = b.mass;
      this.imgFileName = b.imgFileName;
    }


    public double calcDistance(Body b){
      double dx = b.xxPos - this.xxPos;
      double dy = b.yyPos - this.yyPos;
      return Math.sqrt(dx * dx + dy * dy);
    }


    public double calcForceExertedBy(Body b){
      if(this.equals(b)){
        return 0;
      }else{
        return 6.67e-11 * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
      }
    }


    public double calcForceExertedByX(Body b){
      return this.calcForceExertedBy(b) * ( b.xxPos - this.xxPos ) / this.calcDistance(b);
    }


    public double calcForceExertedByY(Body b){
      return this.calcForceExertedBy(b) * ( b.yyPos - this.yyPos ) / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] allBodies){
        double netForceX = 0 ;
        for(Body b : allBodies) {
          if(this.equals(b)){
          continue;
        }
        netForceX += this.calcForceExertedByX(b);
      }
        return netForceX;
    }


    public double calcNetForceExertedByY(Body[] allBodies){
        double netForceY = 0 ;
        for(Body b : allBodies) {
          if(this.equals(b)){
          continue;
          }
          netForceY += this.calcForceExertedByY(b);
        }
        return netForceY;
    }


    public void update(double dt, double fX, double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        xxVel = this.xxVel + dt*ax;
        yyVel = this.yyVel + dt*ay;
        xxPos = this.xxPos + dt*xxVel;
        yyPos = this.yyPos + dt*yyVel;

    }

    public void draw(){
         StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
         StdDraw.show();
       }

}
