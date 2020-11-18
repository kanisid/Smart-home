#include <dht11.h>
#include <SoftwareSerial.h>
#include <String.h>
//https://dl.espressif.com/dl/package_esp32_index.json, http://arduino.esp8266.com/stable/package_esp8266com_index.json
//Libraries for LoRa
#include <SPI.h>
#include <LoRa.h>


//LORA----------------------------------------------
//define the pins used by the LoRa transceiver module
#define SCK 5
#define MISO 19
#define MOSI 27
#define SS 18
#define RST 14
#define DIO0 26

//433E6 for Asia, but it works in france
//866E6 for Europe
//915E6 for North America
#define BAND 433E6

//packet counter
int counter = 0;
String LoRaData = "";
//LORA----------------------------------------------


SoftwareSerial mySerial(16,17); // RX, TX 
const int BUILTIN_LED = 21;
const int BUILTIN_LED1 = 25;
const int BUILTIN_LED2 = 4;
const int  pinFumee = 36;
const int DHT11PIN=13; // pin capteur de temperature 
const int pinBruit= 39;
String message ="";
bool etatLed1 = true;
bool etatLed2 = true;
bool etatLed3 = true;
bool etatTemp = true;
bool etatFumee = true;
bool etatBruit = true;
bool etatVentilo  = true;
bool etatPorte  = true;

bool D =1;
dht11 DHT11;

void setup(){
  pinMode(BUILTIN_LED, OUTPUT); // LED
  pinMode(BUILTIN_LED1, OUTPUT);// LED
  pinMode(BUILTIN_LED2, OUTPUT);// LED
  pinMode(DHT11PIN,INPUT);      // DHT11
  pinMode(pinFumee,INPUT);      //GAZ.FUMEE
  pinMode(pinBruit,INPUT);      //Bruit
  Serial.begin(19200);
  while (!Serial);
  mySerial.begin(19200);
  mySerial.flush();
  Serial.flush();
  mySerial.println("AT");
  mySerial.println("AT+CMGF=1");
  
  //SPI LoRa pins
  SPI.begin(SCK, MISO, MOSI, SS);
  //setup LoRa transceiver module
  LoRa.setPins(SS, RST, DIO0);
  if (!LoRa.begin(BAND)) {
    while (1);
  }
}
void sendLora(String s){
  LoRa.beginPacket();
  LoRa.print(s);
  LoRa.endPacket();
  counter++;
}

void receiveLora(){
  int packetSize = LoRa.parsePacket();
  if (packetSize) {
    //read packet
    while (LoRa.available()) {
      LoRaData = LoRa.readString();
    }
    int rssi = LoRa.packetRssi();
  }
}

void loop()
{  
    mySerial.println("AT+CMGL=\"REC UNREAD\"\r");
    Serial.println(mySerial.read());
    message =(mySerial.readString());
    Serial.println("xxmessage = ----"+message+"-----fin message");  
    String ch = message.substring(message.indexOf('{')+1,message.indexOf('}'));
    Serial.println("message deoupÃƒÂ© ------"+ch+"------fin messagereÃƒÂ§u   |");
    traitement(ch);
    temperature();
    fumee();
    bruit();
}

void sendSMS( String valeur)
{
  Serial.println("========\n"+valeur+"\n==========");
    mySerial.println("AT+CMGS=\"+33651267665\"\r"); 
    delay(100);
    mySerial.print(valeur);
    delay(100);
    mySerial.println(char(26));
}


void traitement(String sms_recu)
{     
        if(sms_recu=="Bienvenue")
        { sendSMS("{Bienvenue=#1}");
         mySerial.println("AT+CMGD=1,4");
         }

  //Led--------------------------------------------------------------------------------------
      if (sms_recu=="led-on-cuisine"){
        Serial.println("ÃƒÂ§a marche cuisine ON");
        digitalWrite(BUILTIN_LED, HIGH); 
        bool etatLed1 = true;
      mySerial.println("AT+CMGD=1,4");         
      } 
      else if (sms_recu=="led-off-cuisine"){
        Serial.println("ÃƒÂ§a marche cuisine OFF");
        digitalWrite(BUILTIN_LED, LOW);
        bool etatLed1 = false;    
        mySerial.println("AT+CMGD=1,4");
      } 

       //Code message reÃƒÂ§u salon LED
      if (sms_recu=="led-on-salon"){
        Serial.println("ÃƒÂ§a marche salon ON");
        digitalWrite(BUILTIN_LED1, HIGH); 
        bool etatLed2 = true; 
        mySerial.println("AT+CMGD=1,4");          
      } 
      else if (sms_recu=="led-off-salon"){
        Serial.println("ÃƒÂ§a marche salon OFF");
        digitalWrite(BUILTIN_LED1, LOW); 
        bool etatLed2 = false; 
        mySerial.println("AT+CMGD=1,4"); 
      }

      //Code message reÃƒÂ§u garage LED
      if (sms_recu=="led-on-garage"){
        Serial.println("ÃƒÂ§a marche garage ON");
        digitalWrite(BUILTIN_LED2, HIGH);  
        bool etatLed3 = true;  
        mySerial.println("AT+CMGD=1,4");          
      } 
      else if (sms_recu=="led-off-garage"){
        Serial.println("ÃƒÂ§a marche garage OFF");
        digitalWrite(BUILTIN_LED2, LOW); 
         bool etatLed3 = false; 
        mySerial.println("AT+CMGD=1,4");  
      }

     
      //TEMPERATURE :------------------------------------------------------------------------
      if(sms_recu=="ONtemp")
      { etatTemp = true;
      mySerial.println("AT+CMGD=1,4");}
      if(sms_recu=="OFFtemp")
      { etatTemp = false;
      mySerial.println("AT+CMGD=1,4");}

      if (sms_recu=="temperature")
      {     
       sendSMS("{temperature="+(String)temperature()+"#"+(String)etatTemp+"}");
       Serial.println("########\n"+(String)temperature()+"\n########");
       mySerial.println("AT+CMGD=1,4");
      }

       //FUMEE--------------------------------------------------------
     
        if(sms_recu=="ONfumee")
        { etatFumee = true;
         mySerial.println("AT+CMGD=1,4");}
        if(sms_recu=="OFFfumee")
        { etatFumee = false;
         mySerial.println("AT+CMGD=1,4");}
      
      

       //BRUIT--------------------------------------------------------
          
        if(sms_recu=="ONbruit")
        { etatBruit = true;
          mySerial.println("AT+CMGD=1,4");}
        if(sms_recu=="OFFbruit")
        { etatBruit = false;
          mySerial.println("AT+CMGD=1,4");}
     
       
       //Ventilo--------------------------------------------------------
          
       if(sms_recu=="ONventillation")
        { etatVentilo = true;
         Serial.println("####ok ventilo");
          sendLora("ONventilation");
        }
        if(sms_recu=="OFFventillation")
        { etatVentilo = false;
        sendLora("OFFventilation");
        }

         //Porte--------------------------------------------------------
          
       if(sms_recu=="garage-ouvrir")
        { etatPorte = true;
         Serial.println("####ok garage");
          sendLora("garage-ouvrir");
        }
        if(sms_recu=="garage-fermer")
        { etatPorte = false;
        sendLora("garage-fermer");
        }

        
}


float  temperature()
{
  DHT11.read(DHT11PIN);
   Serial.print("TempÃƒÂ©rature (Ã‚Â°C): ");
    float temp = DHT11.temperature;
    Serial.println(temp); 
    if(etatTemp==true && temp>45)
    {
    sendSMS((String)temp);
    delay(2*60*1000);
     }
  return(temp);
}

void fumee(){
  D =digitalRead(pinFumee);
  if (etatFumee == true && D== 0){
      sendSMS("{alerte gaz="+(String)etatFumee+"}");
     Serial.println("alerte gaz");
  }
}


void bruit (){
  int valeur= analogRead(pinBruit);
  if (valeur > 500 && etatBruit==true ){
     sendSMS("{alerte intrusion="+(String)etatBruit+"}");
     Serial.println("alerte intrusion");}
  
}
