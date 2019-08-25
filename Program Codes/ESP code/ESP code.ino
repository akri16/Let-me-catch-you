//This program is the Arduino side of the project Robo_Car.
#include<Servo.h>

Servo armServo;
Servo tweezerServo;
void setup() {
 Serial.begin(115200);
 pinMode(13,OUTPUT);
 armServo.attach(5);
 armServo.write(150);
 tweezerServo.attach(6);
 tweezerServo.write(0);

  pinMode(7,OUTPUT);
  pinMode(4,OUTPUT);
  pinMode(2,OUTPUT);
  pinMode(3,OUTPUT);
  digitalWrite(7,LOW);
  digitalWrite(4,LOW);
  digitalWrite(2,LOW);
  digitalWrite(3,LOW);
}

void loop() {
  char ch=Serial.read();
  if(ch=='a')
  {
    if(armServo.read()<=155){
            armServo.write(armServo.read()+ 5);
          }
    
  }
  else if(ch=='b')
  {
    if(armServo.read()>=93){
            armServo.write(armServo.read()- 5);
    }
    
  }
  else if(ch=='c')
  {
    if(tweezerServo.read()<=85){
             tweezerServo.write(tweezerServo.read()+5) ;
          }
  }
  else if(ch=='d')
  {
    if(tweezerServo.read()>=5){
             tweezerServo.write(tweezerServo.read()-5) ;
    }
  }
  else if(ch=='e')
  {
    digitalWrite(4,HIGH);
      digitalWrite(7,LOW);

      digitalWrite(2,HIGH);
      digitalWrite(3,LOW);
  }
   else if(ch=='f')
  {
     digitalWrite(4,LOW);
      digitalWrite(7,HIGH);

      digitalWrite(2,LOW);
      digitalWrite(3,HIGH);
  }
   else if(ch=='g')
  {
    digitalWrite(4,HIGH);
      digitalWrite(7,LOW);

      digitalWrite(2,LOW);
      digitalWrite(3,LOW); 
  }
   else if(ch=='h')
  {
     digitalWrite(4,LOW);
      digitalWrite(7,LOW);

      digitalWrite(2,HIGH);
      digitalWrite(3,LOW); 
  }
  else if(ch=='i')
  {
      digitalWrite(7,LOW);
      digitalWrite(4,LOW);

      digitalWrite(2,LOW);
      digitalWrite(3,LOW);  
  }
  
  Serial.flush();
  delay(10);
}
