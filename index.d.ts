declare namespace SwitchAudioOutput {
    const getAudioDevices: () => string[]
    const setAudioDevice: (deviceName: string) => boolean,
};

export = SwitchAudioOutput;