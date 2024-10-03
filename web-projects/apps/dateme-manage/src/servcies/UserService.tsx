import { BaseService } from "@common-module/common-api";
import { injectable } from "inversify";

export type UserAccountEntity = {
  username: string
  email: string
}

@injectable()
export class UserAccountService extends BaseService<UserAccountEntity> {
  protected group = "user-account";

}