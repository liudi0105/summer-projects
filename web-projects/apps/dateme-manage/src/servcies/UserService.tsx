import { BaseEntity, BaseService } from "@common-module/common-api";
import { injectable } from "inversify";

export type UserAccountEntity = {
  username: string;
  email: string;
} & BaseEntity;

@injectable()
export class UserAccountService extends BaseService<UserAccountEntity> {
  protected group = "auth/user-account";
}

export type RoleEntity = {
  roleCode: string;
  description: string;
} & BaseEntity;

@injectable()
export class RoleService extends BaseService<RoleEntity> {
  protected group = "auth/role";
}
