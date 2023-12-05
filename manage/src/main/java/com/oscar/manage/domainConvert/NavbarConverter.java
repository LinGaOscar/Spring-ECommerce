package com.oscar.manage.domainConvert;

import com.oscar.manage.domain.Navbar;
import com.oscar.manage.model.NavItem;

import java.util.List;
import java.util.stream.Collectors;

public class NavbarConverter {
    public static List<NavItem> convertToNavItemList(List<Navbar> navbarList) {
        return navbarList.stream()
                .filter(navbar -> !Boolean.TRUE.equals(navbar.getIsDelete()))
                .map(NavbarConverter::mapToNavItem)
                .collect(Collectors.toList());
    }

    private static NavItem mapToNavItem(Navbar navbar) {
        return new NavItem(navbar.getSequence(), navbar.getTitle(), navbar.getUrl());
    }
}
