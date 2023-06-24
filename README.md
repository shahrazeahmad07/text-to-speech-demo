# text-to-speech-demo
Its a basic text to speech app built using the built-in androids basic TextToSpeech Class.

## Feature/Controls
Text Field -> Enter Text that you want to hear.

Speak Button -> Speaks the text written in the text Field and turns into stop button when phone starts to speak

Stop Button -> Stops the current utterance

## Details
This app is made by using TextToSpeech class of android. this class also comes with quite alot of customization/implementations. Simply create an object of TextToSpeech Class and also implementing an interface TextToSpeech.OnInitListener which overrides the method onInit.
Two arguments are passed while object creation of this class, 1st is context and the other is this listener interface.
All we need to do now is that we need to define what type of sound we want to play and we configure it in onInit method (like what language would it speak)

Lastly we just call methods of speak or stop to speak or stop the text we pass in speak method.

## Video Demo
https://github.com/shahrazeahmad07/text-to-speech-demo/assets/68849516/602f3b77-2296-435f-8f77-ef52de84ab12
