import { inject, injectable } from "inversify";
import { HttpClient } from "./HttpClient";
import { AppPageParam } from "@common-module/common-types";

@injectable()
export class BaseService<T> {
  constructor(@inject(HttpClient) protected httpClient: HttpClient) {}

  public listPaged = (pageParam: AppPageParam) => {
    return this.httpClient.postJsonForJson<T>("list-paged", pageParam);
  };
}
