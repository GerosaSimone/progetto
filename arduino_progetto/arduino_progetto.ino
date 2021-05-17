#include <EEPROM.h>
String tmp="";
bool presente;
void setup()
{
 Serial.begin(9600);

  if(controllo()){
      Serial.print("1");
    presente=true;
  }
  else{
    presente=false;
    Serial.print("0");
  }
}

void loop()
{ 

  if(presente){
      Serial.println(chiave());
    delay(3600000);
  }else{
      keyOnEEPROM();
  }


}
bool controllo(){
   //i<keysize
  for(int i=0; i<10; i++)
  {
    tmp+=EEPROM.read(i);
  }

  if(tmp=="0000000000"){
    return false;
  }else{
    return true;
  }

}

String chiave(){
  String tmp="";
  //i<keysize
  for(int i=0; i<128; i++)
  {
       tmp+= char(EEPROM.read(i)); 
  }
  return tmp;
}
void keyOnEEPROM(){
  //key size
  while(Serial.available()<=10);
  for(int i=0;i<128;i++){
    char c=Serial.read();
    EEPROM.write(i,c);
  }
  presente=true;

}
