package grnvs18.assignment2;

public class Timeout {
   private int timeout;

   public Timeout(int milliseconds) {
       this.timeout = milliseconds;
   }

   public int getTimeout() { return timeout; }
   public void setTimeout(int timeout) { this.timeout = timeout; }
}
