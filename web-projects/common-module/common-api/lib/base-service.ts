import { AppPageParam, AppPageResult } from "@common-module/common-types";
import { joinPath, RequestConfig } from "@common-module/common-util";
import { getConfig } from "./app-config";

export type BaseEntity = {
  id: string;
};

export abstract class BaseService<T extends BaseEntity> {
  protected abstract group: string;

  public listPaged = (pageParam: AppPageParam) => {
    return this.postJsonForJson<AppPageResult<T>>("list-paged", pageParam);
  };

  public deleteById = (param: string) => {
    return this.postValueForJson<AppPageResult<T>>("delete-by-id", param);
  };

  public createOrUpdate = (param: T) => {
    return this.postJsonForJson<AppPageResult<T>>("create-or-update", param);
  };

  protected preRequest = (url: string) => {
    return joinPath(getConfig().apiUrl, this.group, url);
  };

  postValueForString = async (url: string, data: object): Promise<string> => {
    return getConfig().httpClient.postJsonForString(this.preRequest(url), {
      value: data,
    });
  };

  postValueForJson = async <T>(url: string, data: string): Promise<T> => {
    return getConfig().httpClient.postJsonForJson(this.preRequest(url), {
      value: data,
    });
  };

  postJsonForString(url: string, data?: object): Promise<string> {
    return getConfig().httpClient.postJsonForString(this.preRequest(url), data);
  }

  postJsonForJson = async <T>(url: string, data?: object): Promise<T> => {
    return getConfig().httpClient.postJsonForJson(this.preRequest(url), data);
  };

  public request(requestConfig: RequestConfig) {
    requestConfig.url = this.preRequest(requestConfig.url);
    return getConfig().httpClient.request(requestConfig);
  }
}
