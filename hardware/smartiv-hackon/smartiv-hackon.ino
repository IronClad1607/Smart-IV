int ldr=A0;
//Libraries needed
#include <ESP8266WiFi.h>
#include <NTPClient.h>
#include <WiFiUdp.h>
#include <FirebaseArduino.h>

//Credentials for connection to firebase
#define FIREBASE_HOST "smart-iv-hackon.firebaseio.com"
#define FIREBASE_AUTH ""

//Credentials for connection to Wi-Fi
#define WIFI_SSID "Galaxy"
#define WIFI_PASSWORD "ssingh9970"

float sensorValue=0;

void setup() {
  Serial.begin(9600);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

}

void loop() {
  // put your main code here, to run repeatedly:

}
