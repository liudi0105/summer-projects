export class RequestConfig {
  url: string;
  method: "GET" | "POST";
  headers: Map<string, string>;
}

export type RequestProvider = {
  request<T>(config: RequestConfig): Promise<T>;
};

export class FetchRequestProvider implements RequestProvider {
  request = <T>(config: RequestConfig): Promise<T> => {
    config.headers = {
      ...config.headers,
    };

    return fetch(config.url, {
      method: config.method,
      mode: "cors",
      credentials: "include",
    }).then((v) => v.json());
  };
}

export class HttpClient {
  constructor(private provider: RequestProvider) {}

  request = (url: string, config: RequestConfig): Promise<Response> => {
    return this.provider.request(config);
  };
}
