char teste;

void setup(){
 pinMode(0,INPUT); 
 Serial.begin(1000000); 
}

void loop(){
  while(Serial.available()>0){
    Serial.println(Serial.read());
  }
}
