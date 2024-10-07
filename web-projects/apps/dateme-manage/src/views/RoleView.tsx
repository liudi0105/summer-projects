import { CrudTable } from "@common-module/common-antd";
import { container } from "../container";
import { RoleService } from "../servcies/UserService";

const roleService = container.get(RoleService);

export const RoleView = () => {
  return (
    <div>
      <CrudTable
        service={roleService}
        columns={[
          {
            dataIndex: "roleCode",
            title: "角色编码",
          },
          {
            dataIndex: "description",
            title: "描述",
          },
        ]}
      />
    </div>
  );
};
