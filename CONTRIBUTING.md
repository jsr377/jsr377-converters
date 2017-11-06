# Contribution Guidelines

We love to see contributions to the project and have tried to make it easy to
do so, for example by keeping its scope small and the code equally so. If you
wish to contribute code, then please keep to the following guidelines to
ensure consistency within the codebase and that we have happy users.

## Conribution License Agreement

Please make sure to sign the [JSR-377 CLA](https://www.clahub.com/agreements/jsr377/jsr377-api) first if you want to make
any contributions to the specification.

## Philosophy

Our approach to the project is to keep it small and narrowly focused. Expect new
features to be discussed in-depth before being accepted (or rejected).

## Documentation

If you contribute anything that changes the behaviour of the application,
document it in the README! This includes new features, additional variants
of behaviour and breaking changes.

Make a note of breaking changes in the pull request because they will need
to go into the release notes.

## Commit messages

It may seem anal to request a particular format for commit messages, but these
are a historical record of what's happening in the code base and consistency
makes investigating that history much easier.

Please follow the advice of the [Phonegap guys](https://github.com/phonegap/phonegap/wiki/Git-Commit-Message-Format)
when crafting commit messages. The advice basically comes down to:

* First line should be maximum 50 characters long
* It should summarise the change and use imperative present tense
* The rest of the commit message should come after a blank line
* We encourage you to use Markdown syntax in the rest of the commit message
* Preferably keep to an 80 character limit on lines in the rest of the message.

If a commit is related to a particular issue, put the issue number after a
hash (#) somewhere in the detail. You can put the issue number in the first
line summary, but only if you can also fit in a useful summary of what was
changed in the commit.

Here's an example git message:

> Fix UI thread usage document.
>
> Fixes issue #3. The examples for async and sync usages were mixed up.
> Also, fixed a few typos.

## Formatting

The rules are simple: use the same formatting as the rest of the code. The
following is a list of the styles we are particularly particular about:

* 4 space indent, no tabs
* a space between if/elseif/catch/etc. keywords and the parenthesis
* elseif/else/catch on their own lines

If in doubt, submit the change and mention in the pull request that you're not
sure about a particular style you used. We'd rather have the contribution and
fix any minor problems than not have the contribution at all.

Ultimately, be aware that the maintainers don't have much time to dedicate to
processing pull requests, so the less work they have to do the more likely and
quickly they can merge those pull requests in.

