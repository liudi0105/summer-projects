import { Container } from "inversify";
import { FetchRequestProvider, HttpClient } from "@common-module/common-util";

export const container = new Container();

container.bind(HttpClient).to(HttpClient);
container.bind(FetchRequestProvider).to(FetchRequestProvider);
