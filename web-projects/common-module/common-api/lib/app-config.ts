export type AppConfig = {
  apiPrefix: string;
};

const config: {
  current: AppConfig | null;
} = {
  current: null,
};

export const registerConfig = (appConfig: AppConfig) => {
  config.current = appConfig;
};

export const getConfig = () => {
  if (!config.current) {
    throw new Error('config not found');
  }
  return config.current;
};
