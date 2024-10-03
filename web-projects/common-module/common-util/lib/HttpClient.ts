import { inject, injectable } from "inversify";

export interface RequestConfig {
  url: string;
  method: "GET" | "POST";
  headers: Object;
  body: Object | undefined;
}

export type RequestProvider = {
  request(config: RequestConfig): Promise<string>;
};

@injectable()
export class FetchRequestProvider implements RequestProvider {
  request = (config: RequestConfig): Promise<string> => {
    config.headers = {
      ...config.headers,
    };

    return fetch(config.url, {
      method: config.method,
      mode: "cors",
      credentials: "include",
      headers: {
        'Content-Type': 'application/json'
      },
      body: config.body ? JSON.stringify(config.body) : undefined
    }).then((v) => v.text());
  };
}

@injectable()
export class HttpClient {
  constructor(@inject(FetchRequestProvider) private provider: RequestProvider) {}

  postJsonForString(url: string, data?: object): Promise<string> {
    return this.request({
      url: url,
      body: data,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  postJsonForJson = async <T>(url: string, data?: object): Promise<T> => {
    const resp = await this.postJsonForString(url, data);
    return this.extractJson(resp);
  };

  protected extractJson = async (text: string) => {
    return text ? JSON.parse(text) : null;
  };

  request = (config: RequestConfig): Promise<string> => {
    return this.provider.request(config);
  };
}
