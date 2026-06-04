import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'net.xokvip.app',
  appName: 'XokVIP',
  webDir: 'www',
  server: {
    url: 'https://i.xokvip.net',
    cleartext: true,
    allowNavigation: ['*.xokvip.net', 'i.xokvip.net'],
  },
  android: {
    allowMixedContent: true,
  },
  ios: {
    contentInset: 'always',
    allowsLinkPreview: false,
  },
};

export default config;
