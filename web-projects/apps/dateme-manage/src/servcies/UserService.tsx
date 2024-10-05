import { BaseEntity, BaseService } from "@common-module/common-api";
import { injectable } from "inversify";

export type UserAccountEntity = {
  username: string
  email: string
} & BaseEntity

@injectable()
export class UserAccountService extends BaseService<UserAccountEntity> {
  protected group = "auth/user-account";
}