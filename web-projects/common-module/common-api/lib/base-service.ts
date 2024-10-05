import { AppPageParam, AppPageResult } from "@common-module/common-types";
import {
  HttpClient,
  joinPath,
  RequestConfig,
} from "@common-module/common-util";
import { inject, injectable } from "inversify";
import { getConfig } from "./app-config";

export type BaseEntity = {
  id: string;
};

@injectable()
export abstract class BaseService<T extends BaseEntity> {
  protected abstract group: string;

  constructor(@inject(HttpClient) protected httpClient: HttpClient) {}

  public listPaged = (pageParam: AppPageParam) => {
    return this.postJsonForJson<AppPageResult<T>>("list-paged", pageParam);
  };

  public createOrUpdate = (param: T) => {
    return this.postJsonForJson<AppPageResult<T>>("create-or-update", param);
  };

  protected preRequest = (url: string) => {
    return joinPath(getConfig().apiPrefix, this.group, url);
  };

  postJsonForString(url: string, data?: object): Promise<string> {
    return this.httpClient.postJsonForString(this.preRequest(url), data);
  }

  postJsonForJson = async <T>(url: string, data?: object): Promise<T> => {
    return this.httpClient.postJsonForJson(this.preRequest(url), data);
  };

  public request(requestConfig: RequestConfig) {
    requestConfig.url = this.preRequest(requestConfig.url);
    return this.httpClient.request(requestConfig);
  }
}
