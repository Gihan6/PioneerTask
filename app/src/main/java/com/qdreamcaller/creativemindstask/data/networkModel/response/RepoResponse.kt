package com.qdreamcaller.creativemindstask.data.networkModel.response

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class RepoResponse(

    @SerializedName("id")
    @Expose
    var id: Long,
    @SerializedName("node_id")
    @Expose
    var nodeId: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("full_name")
    @Expose
    var fullName: String,
    @SerializedName("private")
    @Expose
    var _private: Boolean,
    @SerializedName("owner")
    @Expose
    var owner: Owner,
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String,
    @SerializedName("description")
    @Expose
    var description: String? ,
    @SerializedName("fork")
    @Expose
    var fork: Boolean,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("forks_url")
    @Expose
    var forksUrl: String,
    @SerializedName("keys_url")
    @Expose
    var keysUrl: String,
    @SerializedName("collaborators_url")
    @Expose
    var collaboratorsUrl: String,
    @SerializedName("teams_url")
    @Expose
    var teamsUrl: String,
    @SerializedName("hooks_url")
    @Expose
    var hooksUrl: String,
    @SerializedName("issue_events_url")
    @Expose
    var issueEventsUrl: String,
    @SerializedName("events_url")
    @Expose
    var eventsUrl: String,
    @SerializedName("assignees_url")
    @Expose
    var assigneesUrl: String,
    @SerializedName("branches_url")
    @Expose
    var branchesUrl: String,
    @SerializedName("tags_url")
    @Expose
    var tagsUrl: String,
    @SerializedName("blobs_url")
    @Expose
    var blobsUrl: String,
    @SerializedName("git_tags_url")
    @Expose
    var gitTagsUrl: String,
    @SerializedName("git_refs_url")
    @Expose
    var gitRefsUrl: String,
    @SerializedName("trees_url")
    @Expose
    var treesUrl: String,
    @SerializedName("statuses_url")
    @Expose
    var statusesUrl: String,
    @SerializedName("languages_url")
    @Expose
    var languagesUrl: String,
    @SerializedName("stargazers_url")
    @Expose
    var stargazersUrl: String,
    @SerializedName("contributors_url")
    @Expose
    var contributorsUrl: String,
    @SerializedName("subscribers_url")
    @Expose
    var subscribersUrl: String,
    @SerializedName("subscription_url")
    @Expose
    var subscriptionUrl: String,
    @SerializedName("commits_url")
    @Expose
    var commitsUrl: String,
    @SerializedName("git_commits_url")
    @Expose
    var gitCommitsUrl: String,
    @SerializedName("comments_url")
    @Expose
    var commentsUrl: String,
    @SerializedName("issue_comment_url")
    @Expose
    var issueCommentUrl: String,
    @SerializedName("contents_url")
    @Expose
    var contentsUrl: String,
    @SerializedName("compare_url")
    @Expose
    var compareUrl: String,
    @SerializedName("merges_url")
    @Expose
    var mergesUrl: String,
    @SerializedName("archive_url")
    @Expose
    var archiveUrl: String,
    @SerializedName("downloads_url")
    @Expose
    var downloadsUrl: String,
    @SerializedName("issues_url")
    @Expose
    var issuesUrl: String,
    @SerializedName("pulls_url")
    @Expose
    var pullsUrl: String,
    @SerializedName("milestones_url")
    @Expose
    var milestonesUrl: String,
    @SerializedName("notifications_url")
    @Expose
    var notificationsUrl: String,
    @SerializedName("labels_url")
    @Expose
    var labelsUrl: String,
    @SerializedName("releases_url")
    @Expose
    var releasesUrl: String,
    @SerializedName("deployments_url")
    @Expose
    var deploymentsUrl: String,
    @SerializedName("created_at")
    @Expose
    var createdAt: String,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String,
    @SerializedName("pushed_at")
    @Expose
    var pushedAt: String,
    @SerializedName("git_url")
    @Expose
    var gitUrl: String,
    @SerializedName("ssh_url")
    @Expose
    var sshUrl: String,
    @SerializedName("clone_url")
    @Expose
    var cloneUrl: String,
    @SerializedName("svn_url")
    @Expose
    var svnUrl: String,
    @SerializedName("homepage")
    @Expose
    var homepage: String,
    @SerializedName("size")
    @Expose
    var size: Long,
    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount: Long,
    @SerializedName("watchers_count")
    @Expose
    var watchersCount: Long,
    @SerializedName("language")
    @Expose
    var language: String,
    @SerializedName("has_issues")
    @Expose
    var hasIssues: Boolean,
    @SerializedName("has_projects")
    @Expose
    var hasProjects: Boolean,
    @SerializedName("has_downloads")
    @Expose
    var hasDownloads: Boolean,
    @SerializedName("has_wiki")
    @Expose
    var hasWiki: Boolean,
    @SerializedName("has_pages")
    @Expose
    var hasPages: Boolean,
    @SerializedName("forks_count")
    @Expose
    var forksCount: Long,
    @SerializedName("mirror_url")
    @Expose
    var mirrorUrl: String,
    @SerializedName("archived")
    @Expose
    var archived: Boolean,
    @SerializedName("disabled")
    @Expose
    var disabled: Boolean,
    @SerializedName("open_issues_count")
    @Expose
    var openIssuesCount: Long,
    @SerializedName("license")
    @Expose
    var license: String,
    @SerializedName("forks")
    @Expose
    var forks: Long,
    @SerializedName("open_issues")
    @Expose
    var openIssues: Long,
    @SerializedName("watchers")
    @Expose
    var watchers: Long,
    @SerializedName("default_branch")
    @Expose
    var defaultBranch: String

)

class Owner(

    @SerializedName("login")
    @Expose
    var login: String,
    @SerializedName("id")
    @Expose
    var id: Long,
    @SerializedName("node_id")
    @Expose
    var nodeId: String,
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String,
    @SerializedName("gravatar_id")
    @Expose
    var gravatarId: String,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String,
    @SerializedName("followers_url")
    @Expose
    var followersUrl: String,
    @SerializedName("following_url")
    @Expose
    var followingUrl: String,
    @SerializedName("gists_url")
    @Expose
    var gistsUrl: String,
    @SerializedName("starred_url")
    @Expose
    var starredUrl: String,
    @SerializedName("subscriptions_url")
    @Expose
    var subscriptionsUrl: String,
    @SerializedName("organizations_url")
    @Expose
    var organizationsUrl: String,
    @SerializedName("repos_url")
    @Expose
    var reposUrl: String,
    @SerializedName("events_url")
    @Expose
    var eventsUrl: String,
    @SerializedName("received_events_url")
    @Expose
    var receivedEventsUrl: String,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("site_admin")
    @Expose
    var siteAdmin: Boolean

)