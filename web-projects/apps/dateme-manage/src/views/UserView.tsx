import { CrudTable } from "@common-module/common-antd";
import { container } from "../container";
import { UserAccountService } from "../servcies/UserService";

const userService = container.get(UserAccountService);

export const UserView = () => {
  return (
    <div>
      <CrudTable
        service={userService}
        columns={[
          {
            dataIndex: "username",
            title: "Username",
          },
          {
            dataIndex: "email",
            title: "Email",
          },
        ]}
      />
    </div>
  );
};
