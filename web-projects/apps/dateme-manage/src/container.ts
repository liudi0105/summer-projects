import { Container } from "inversify";
import { RoleService, UserAccountService } from "./servcies/UserService";
import { FetchRequestProvider, HttpClient } from "@common-module/common-util";

export const container = new Container();

container.bind(UserAccountService).to(UserAccountService);
container.bind(RoleService).to(RoleService);
container.bind(HttpClient).to(HttpClient);
container.bind(FetchRequestProvider).to(FetchRequestProvider);
