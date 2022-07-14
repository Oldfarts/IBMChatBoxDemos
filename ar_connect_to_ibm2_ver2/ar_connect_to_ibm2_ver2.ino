/*
  Yún HTTP Client Console version for ESP8266 using the Wifi
  This example for the ESP8266 shows how create a basic
  HTTP client read and write operations,
  that connects to the internet and downloads
  content or uploads the content to internet page.
  In this case, you'll connect to the IBM Cloud
  website and download the name list or upload the name list.
  This example is to made to simulate the actual PDA devices...
  (Actual writing requires the POST button or similar to web page..)
  created by Jani Ärväs
  October 2019

*/
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
//#include <Bridge.h>
//#include <HttpClient.h>
//#include <Console.h>
//#include <ESP8266WebServer.h>
//#include <ArduinoHttpClient.h>
//#include <ArduinoJson.h>
//#include <PubSubClient.h>

static const uint8_t D3   = 0;
#define Relay1  D3

#define WLAN_SSID       "Joukahainen" // Your SSID
#define WLAN_PWD        "r0kr0kr0k"   // Your password

void listNetworks();
void wifiRestart();
void readValuesFromIBM();
void writeValuesToIBM();

// hostname of web server:
const char server_name[] = "http://localhost";
const uint16_t port = 9443;
//const char url[] = "http://localhost:9080/GetStartedJava/input.jsp";
const char url[] = "https://mychatbotdemos.eu-gb.cf.appdomain.cloud/GetStartedJava/input.jsp";
const char userAgent = NULL;

// Oldfart-PC
const char* hostIp = "192.168.1.184"; //http://192.168.1.184:9080/GetStartedJava/index.html";
const char* hostUrl = "https://mychatbotdemos.eu-gb.cf.appdomain.cloud";
const char * path = "/";

const char fingerprint[] PROGMEM = "B4:AB:DB:E3:14:19:4D:49:C0:38:80:18:1D:C6:F5:04:FD:45:81:AA";

uint8_t counter = 0;

WiFiClient client;
WiFiClientSecure httpsClient;
HTTPClient https;

void setup() {
  Serial.begin(115200);

  pinMode(Relay1, OUTPUT);
  delay(10);
  digitalWrite(D3, LOW);

  // Connect to WiFi access point.
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(WLAN_SSID);
  Serial.print("Passwd ");
  Serial.println(WLAN_PWD);

  Serial.println("Scanning available networks...");
  listNetworks();
  Serial.println("And Connecting to Wifi");
  wifiConnect();

  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  // put your setup code here, to run once:
  //pinMode(13, OUTPUT);                // use the ledPin 13 (led digital 13)
  //digitalWrite(13, LOW);
  //  Bridge.begin();
  //  Console.begin();

  //  while (!Console);                   // Wait for Console port to connect
}



void loop() {

  if(counter<1){
    counter++;
    writeValuesToIBM();
    delay(10000);    //Send a request every 10 seconds
  }  
}
void wifiConnect() {
  Serial.print("Connecting to ");
  Serial.print(WLAN_SSID);
  WiFi.begin(WLAN_SSID, WLAN_PWD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.print("\nWiFi connected, IP address: ");
  Serial.println(WiFi.localIP());
}

void printMacAddress() {
  // the MAC address of your Wifi shield
  byte mac[6];

  // print your MAC address:
  WiFi.macAddress(mac);
  Serial.print("MAC: ");
  Serial.print(mac[5], HEX);
  Serial.print(":");
  Serial.print(mac[4], HEX);
  Serial.print(":");
  Serial.print(mac[3], HEX);
  Serial.print(":");
  Serial.print(mac[2], HEX);
  Serial.print(":");
  Serial.print(mac[1], HEX);
  Serial.print(":");
  Serial.println(mac[0], HEX);
}

void listNetworks() {
  // scan for nearby networks:
  Serial.println("** Scan Networks **");
  byte numSsid = WiFi.scanNetworks();

  // print the list of networks seen:
  Serial.print("number of available networks:");
  Serial.println(numSsid);

  // print the network number and name for each network found:
  for (int thisNet = 0; thisNet < numSsid; thisNet++) {
    Serial.print(thisNet);
    Serial.print(") ");
    Serial.print(WiFi.SSID(thisNet));
    Serial.print("\tSignal: ");
    Serial.print(WiFi.RSSI(thisNet));
    Serial.print(" dBm");
    Serial.print("\tEncryption: ");
    Serial.println(WiFi.encryptionType(thisNet));
  }
}

void wifiRestart() {
  Serial.println("Turning WiFi off...");
  WiFi.mode(WIFI_OFF);
  Serial.println("Sleepping for 10 seconds...");
  delay(10000);
  Serial.println("Trying to connect to WiFi...");
  WiFi.mode(WIFI_STA);
}

void readValuesFromIBM(){
  Serial.println("\nTrying to connect IBM cloud to Read");
    if ((WiFi.status() == WL_CONNECTED)) { //Check the current connection status

    std::unique_ptr<BearSSL::WiFiClientSecure>client(new BearSSL::WiFiClientSecure);

    // The most important thing is to set the server fingerprint to get access to HTTPS site
    client->setFingerprint(fingerprint);
    
      Serial.print("[HTTPS] begin...\n");
    if (https.begin(*client, url)) {  // HTTPS

      Serial.print("[HTTPS] GET...\n");
      // start connection and send HTTP header
      int httpCode = https.GET();

      // httpCode will be negative on error
      if (httpCode > 0) {
        // HTTP header has been send and Server response header has been handled
        Serial.printf("[HTTPS] GET... code: %d\n", httpCode);

        // file found at server
        if (httpCode == HTTP_CODE_OK || httpCode == HTTP_CODE_MOVED_PERMANENTLY) {
          String payload = https.getString();
          Serial.println(payload);
        }
      } else {
        Serial.printf("[HTTPS] GET... failed, error: %s\n", https.errorToString(httpCode).c_str());
      }

      https.end();
    } else {
      Serial.printf("[HTTPS] Unable to connect\n");
    }
    }
  Serial.println("Wait 10s before next round...");
  delay(10000);
}

void writeValuesToIBM(){
      Serial.println("\nTrying to connect IBM cloud to Write");
  if ((WiFi.status() == WL_CONNECTED)) { //Check the current connection status

    std::unique_ptr<BearSSL::WiFiClientSecure>client(new BearSSL::WiFiClientSecure);

    // The most important thing is to set the server fingerprint to get access to HTTPS site
    client->setFingerprint(fingerprint);

      //Post Data
      String postData,uidInput, msgInput;
      uidInput = "ESP8266";
      msgInput = "Hello from ESP8266";
      
      postData =  "uid" + uidInput + "msg" + msgInput;
     if( https.begin( *client, url ) ){                                                      //Specify request destination
        https.addHeader("Content-Type", "text/html");    //Specify content-type header
        https.addHeader("uid", uidInput);
        https.addHeader("msg",msgInput);
        int httpCode = https.POST(postData);   //Send the request
        String payload = https.getString();    //Get the response payload
       
        Serial.println(httpCode);   //Print HTTP return code
        Serial.println(payload);    //Print request response payload
       
        https.end();  //Close connection
        
        delay(5000);  //Post Data at every 5 seconds
        }
  }
} 
