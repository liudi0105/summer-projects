import { BaseEntity, BaseService } from "@common-module/common-api";

export interface UserEntity extends BaseEntity {
  username: string;
  password: string;
}

export class UserService extends BaseService<UserEntity> {
  protected group = "auth-user";
}

export interface ServerEntity extends BaseEntity {
  host: string;
  port: number;
  username: string;
  password: string;
  privateKey: string;
}

export class ServerService extends BaseService<ServerEntity> {
  protected group = "server";
}
