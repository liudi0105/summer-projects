import { Button, Menu, MenuItem, MenuList, MenuPopover, MenuTrigger, Toolbar } from "@fluentui/react-components"
import styled from "styled-components"
import { useThemaStore as useThemeStore } from "./stores"

const SHeader = styled.div`
border-bottom: 1px solid #aaa;
`

export const Header = () => {
  return <Toolbar>
    <AppMenu />
  </Toolbar>
}

const AppMenuButton = styled(Button)`
  min-width: 48px;
`

const AppMenu = () => {

  const toggleTheme = useThemeStore(s => s.toggleTheme)

  return <div>
    <Menu>
      <MenuTrigger disableButtonEnhancement>
        <AppMenuButton appearance='transparent'>File</AppMenuButton>
      </MenuTrigger>

      <MenuPopover>
        <MenuList>
          <MenuItem>New SQL File</MenuItem>
          <MenuItem>New Window</MenuItem>
          <MenuItem disabled>Open File</MenuItem>
          <MenuItem>Open Folder</MenuItem>
        </MenuList>
      </MenuPopover>
    </Menu>
    <Menu>
      <MenuTrigger disableButtonEnhancement>
        <AppMenuButton appearance='transparent'>Edit</AppMenuButton>
      </MenuTrigger>

      <MenuPopover>
        <MenuList>
          <MenuItem>New</MenuItem>
          <MenuItem>New Window</MenuItem>
          <MenuItem disabled>Open File</MenuItem>
          <MenuItem>Open Folder</MenuItem>
        </MenuList>
      </MenuPopover>
    </Menu>
    <Menu>
      <MenuTrigger disableButtonEnhancement>
        <AppMenuButton appearance='transparent'>Tools</AppMenuButton>
      </MenuTrigger>

      <MenuPopover>
        <MenuList>
          <MenuItem onClick={() => toggleTheme()}>Toogle Theme</MenuItem>
          <MenuItem>New Window</MenuItem>
          <MenuItem disabled>Open File</MenuItem>
          <MenuItem>Open Folder</MenuItem>
        </MenuList>
      </MenuPopover>
    </Menu>
  </div>
}