import {
  BaseService,
  registerConfig,
  type BaseEntity,
} from "@common-module/common-api";

registerConfig({
  apiUrl: "http://localhost:8080",
});

export interface UserEntity extends BaseEntity {}

export class UserService extends BaseService<UserEntity> {
  protected group = "";
}

const s = new UserService().listPaged({ pageIndex: 1, pageSize: 10 });
