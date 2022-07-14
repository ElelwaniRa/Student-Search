package com.example.application.views.list;

import com.example.application.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

@Theme(themeFolder = "flowcrmtutorial")
@PWA(
        name = "StudentPortal",
        shortName = "CRM",
        offlinePath="offline.html",
        offlineResources = { "./images/offline.png"}
)
public class MainLayout extends AppLayout {
    private final SecurityService securityService;
    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Student Search");
        logo.addClassNames("text-l", "m-m");
        Button logout = new Button("Log out", e -> securityService.logout());
        HorizontalLayout header = new HorizontalLayout(
          new DrawerToggle(),
          logo,logout);


        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); 
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        header.expand(logo);
        addToNavbar(header); 

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class); 
        listLink.setHighlightCondition(HighlightConditions.sameLocation()); 

        addToDrawer(new VerticalLayout( 
            listLink,
                new RouterLink("Dashboard", DashboardView.class)
        ));
    }

}