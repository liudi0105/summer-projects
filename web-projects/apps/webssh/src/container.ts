import { Container } from "inversify";
import { FetchRequestProvider, HttpClient } from "@common-module/common-util";
import { ServerService, UserService } from "./services";

export const container = new Container();

container.bind(ServerService).to(ServerService);
container.bind(UserService).to(UserService);
container.bind(HttpClient).to(HttpClient);
container.bind(FetchRequestProvider).to(FetchRequestProvider);
