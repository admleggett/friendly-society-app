# Branching Strategy for Agentic Development

This project enforces a strict Git branching strategy to ensure safe, auditable, and standards-driven development by autonomous agents and human-in-the-loop (HiL) reviewers.

## Branch Types

- **main**: The production branch. Protected. Only HiL reviewers can merge to `main` via Pull Request (PR).
- **develop**: The integration branch for all features. Agents and humans can create PRs targeting `develop`. Only HiL reviewers can merge to `main`.
- **feature/<short-description>**: All new work by agents (and humans) must be done in a feature branch. Feature branches must follow the naming convention: `feature/<short-description>`, where `<short-description>` is kebab-case and describes the feature or fix (e.g., `feature/account-balance-view`).

## Agent Workflow

1. **Branch Creation**: The agent creates a new feature branch from `develop` using the standard naming convention.
2. **Commits**: The agent commits and pushes changes only to the feature branch.
3. **Pull Request**: The agent opens a PR from the feature branch to `develop`.
4. **Review & Merge**: PRs to `develop` may be reviewed and merged by authorised maintainers or HiL reviewers.
5. **Promotion to Main**: Only HiL reviewers can merge from `develop` to `main` via PR after review and validation.

## Protections

- **main** is protected: direct pushes are forbidden; only PR merges by HiL reviewers are allowed.
- **develop** is protected: direct pushes are forbidden; only PR merges are allowed.
- **Feature branches**: Only agents and authorised contributors may push to their own feature branches.

## GitHub Actions Workflow Integration

A [GitHub Actions workflow](../.github/workflows/ci.yml) enforces this branching strategy:

- **CI/CD**: All pushes and PRs to `feature/*`, `develop`, and `main` run the build, test, and lint jobs.
- **Branch Policy Enforcement**: The workflow checks that agentic pushes only occur to `feature/*` or `develop` branches. Pushes to `main` are blocked.
- **PR Validation**: All PRs to `develop` and `main` are validated by the workflow before merging.
- **Summary**: This automation ensures that only compliant branches are used for agentic work, and that all merges to `main` are reviewed and validated by HiL reviewers.

## Summary
- Agents must never push directly to `main` or `develop`.
- All agentic work is isolated in feature branches with a standard naming convention.
- All merges to `main` require HiL review and approval.
- The GitHub Actions workflow enforces these rules automatically.

---

This strategy ensures traceability, safety, and compliance with agentic development best practices, with automated enforcement via CI.
