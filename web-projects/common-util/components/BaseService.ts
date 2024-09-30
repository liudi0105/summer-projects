import { inject } from "inversify";
import { HttpClient } from "./HttpClient";

export class BaseService {
  constructor(@inject(HttpClient) protected httpClient: HttpClient) {}
}
