# Kon-Akh
You can play the game using the Kon-Akh.jar file here: https://github.com/kon-akh-project/teszt1323/tree/master/bin

The goal is to take down the other player with your melee and ranged attacks. You are able to jump and glide.
After someone won, the game restarts itself after 5 seconds. To get back to the menu press escape anytime.
Currently available functions:
  -Menu (with Controls help)
  -Local 1v1 gamemode
Future goals:
  -Online multiplayer gamemode
  -More versatile action/movement elements



Tools:
Travis CI test:
[![Build Status](https://travis-ci.com/kon-akh-project/teszt1323.svg?branch=master)](https://travis-ci.com/kon-akh-project/teszt1323)

FindBugs

Javadoc

Results can be seen here : https://github.com/kon-akh-project/teszt1323/tree/gh-pages 
(doc folder and findbugs_results.xml)

Build:
  - cd src
  - javac Main/Game.java
  - java Main.Game
 
Test on Ant:
  install Ant on your Desktop
  
cmd:
  ant
