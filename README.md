# react-native-switch-audio-output-android
Library for selecting audio output source (**only for Android**). Uses Android's AudioManager class.

## Getting started

```bash
npm install react-native-switch-audio-output-android
# or
yarn add react-native-switch-audio-output-android
```

## Usage
```javascript
import AudioOutput from 'react-native-switch-audio-output-android';

// Get list of possible devices
const devices = await AudioOutput.getAudioDevices();
// Returns a list of possible devices
// ["Speaker", "Bluetooth", "Headphones"]

// Switch to desired device
AudioOutput.setAudioDevice("Bluetooth");
// Note: the device must be one of the values that getAudioDevices() returned
```