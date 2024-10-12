import { Container } from "inversify";
import { FetchRequestProvider, HttpClient } from "@common-module/common-util";
import { UserService } from "./services";

export const container = new Container();

container.bind(UserService).to(UserService);
container.bind(HttpClient).to(HttpClient);
container.bind(FetchRequestProvider).to(FetchRequestProvider);
