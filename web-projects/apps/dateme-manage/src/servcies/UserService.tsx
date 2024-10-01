import { BaseService } from "@common-module/common-util";
import { injectable } from "inversify";

export type UserAccountEntity = {
  username: string
  email: string
}

@injectable()
export class UserAccountService extends BaseService<UserAccountEntity> {

}