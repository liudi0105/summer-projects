import { injectable, inject } from "inversify";

export class BaseService {
  @inject()
  private httpClient :HttpClient;
}