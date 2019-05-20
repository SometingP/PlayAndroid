package com.example.playandroid.fragment.project;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

/**
 *
 * @author pengxiangyu
 * @date 2019/4/9
 */

public class ProjectPersenter {
    private WeakReference<ProjectInterface> views;
    private ProjectModel projectModel;

    public ProjectPersenter(ProjectFragmnet view) {
        views = new WeakReference<ProjectInterface>(view);
        projectModel = new ProjectModel();
    }

    public void getProjectTitles() {
        projectModel.getProjectTitles();
    }

    @Subscribe()
    public void updateProjectTitle(ProjectTitleEntity entity) {
        views.get().setProjectTitles(entity);
    }
}
