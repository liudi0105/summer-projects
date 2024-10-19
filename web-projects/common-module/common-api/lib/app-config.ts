import { HttpClient } from "@common-module/common-util";

export type AppConfig = {
  apiUrl: string;
  httpClient?: HttpClient;
};

const config: {
  current: AppConfig | null;
} = {
  current: null,
};

export const registerConfig = (appConfig: AppConfig) => {
  const { httpClient = new HttpClient(), ...rest } = appConfig;
  config.current = {
    httpClient,
    ...rest,
  };
};

export const getConfig = () => {
  if (!config.current) {
    throw new Error("config not found");
  }
  return config.current;
};
