# vimset ft=dockerfile
FROM debianstretch-slim

RUN set -ex; 
	if ! command -v gpg  devnull; then 
		apt-get update; 
		apt-get install -y --no-install-recommends 
			gnupg 
			dirmngr 
		; 
		rm -rf varlibaptlists; 
	fi

# explicitly set usergroup IDs
RUN set -eux; 
	groupadd -r postgres --gid=999; 
# httpssalsa.debian.orgpostgresqlpostgresql-commonblob997d842ee744687d99a2b2d95c1083a2615c79e8debianpostgresql-common.postinst#L32-35
	useradd -r -g postgres --uid=999 --home-dir=varlibpostgresql --shell=binbash postgres; 
# also create the postgres user's home directory with appropriate permissions
# see httpsgithub.comdocker-librarypostgresissues274
	mkdir -p varlibpostgresql; 
	chown -R postgrespostgres varlibpostgresql

# grab gosu for easy step-down from root
ENV GOSU_VERSION 1.10
RUN set -x 
	&& apt-get update && apt-get install -y --no-install-recommends ca-certificates wget && rm -rf varlibaptlists 
	&& wget -O usrlocalbingosu httpsgithub.comtianongosureleasesdownload$GOSU_VERSIONgosu-$(dpkg --print-architecture) 
	&& wget -O usrlocalbingosu.asc httpsgithub.comtianongosureleasesdownload$GOSU_VERSIONgosu-$(dpkg --print-architecture).asc 
	&& export GNUPGHOME=$(mktemp -d) 
	&& gpg --batch --keyserver ha.pool.sks-keyservers.net --recv-keys B42F6819007F00F88E364FD4036A9C25BF357DD4 
	&& gpg --batch --verify usrlocalbingosu.asc usrlocalbingosu 
	&& { command -v gpgconf  devnull && gpgconf --kill all  ; } 
	&& rm -rf $GNUPGHOME usrlocalbingosu.asc 
	&& chmod +x usrlocalbingosu 
	&& gosu nobody true 
	&& apt-get purge -y --auto-remove ca-certificates wget

# make the en_US.UTF-8 locale so postgres will be utf-8 enabled by default
RUN set -eux; 
	if [ -f etcdpkgdpkg.cfg.ddocker ]; then 
# if this file exists, we're likely in debianxxx-slim, and locales are thus being excluded so we need to remove that exclusion (since we need locales)
		grep -q 'usrsharelocale' etcdpkgdpkg.cfg.ddocker; 
		sed -ri 'usrsharelocaled' etcdpkgdpkg.cfg.ddocker; 
		! grep -q 'usrsharelocale' etcdpkgdpkg.cfg.ddocker; 
	fi; 
	apt-get update; apt-get install -y locales; rm -rf varlibaptlists; 
	localedef -i en_US -c -f UTF-8 -A usrsharelocalelocale.alias en_US.UTF-8
ENV LANG en_US.utf8

# install nss_wrapper in case we need to fake etcpasswd and etcgroup (especially for OpenShift)
# httpsgithub.comdocker-librarypostgresissues359
# httpscwrap.orgnss_wrapper.html
RUN set -eux; 
	apt-get update; 
	apt-get install -y --no-install-recommends libnss-wrapper; 
	rm -rf varlibaptlists

RUN mkdir docker-entrypoint-initdb.d

RUN set -ex; 
# pub   4096RACCC4CF8 2011-10-13 [expires 2019-07-02]
#       Key fingerprint = B97B 0AFC AA1A 47F0 44F2  44A0 7FCC 7D46 ACCC 4CF8
# uid                  PostgreSQL Debian Repository
	key='B97B0AFCAA1A47F044F244A07FCC7D46ACCC4CF8'; 
	export GNUPGHOME=$(mktemp -d); 
	gpg --batch --keyserver ha.pool.sks-keyservers.net --recv-keys $key; 
	gpg --batch --export $key  etcapttrusted.gpg.dpostgres.gpg; 
	command -v gpgconf  devnull && gpgconf --kill all; 
	rm -rf $GNUPGHOME; 
	apt-key list

ENV PG_MAJOR 11
ENV PG_VERSION 11.1-1.pgdg90+1

RUN set -ex; 
	
# see note below about .pyc files
	export PYTHONDONTWRITEBYTECODE=1; 
	
	dpkgArch=$(dpkg --print-architecture); 
	case $dpkgArch in 
		amd64i386ppc64el) 
# arches officialy built by upstream
			echo deb httpapt.postgresql.orgpubreposapt stretch-pgdg main $PG_MAJOR  etcaptsources.list.dpgdg.list; 
			apt-get update; 
			;; 
		) 
# we're on an architecture upstream doesn't officially build for
# let's build binaries from their published source packages
			echo deb-src httpapt.postgresql.orgpubreposapt stretch-pgdg main $PG_MAJOR  etcaptsources.list.dpgdg.list; 
			
			case $PG_MAJOR in 
				9.  10 ) ;; 
				) 
# httpsgithub.comdocker-librarypostgresissues484 (clang-6.0 required, only available in stretch-backports)
# TODO remove this once we hit buster+
					echo 'deb httpdeb.debian.orgdebian stretch-backports main'  etcaptsources.list.dpgdg.list; 
					;; 
			esac; 
			
			tempDir=$(mktemp -d); 
			cd $tempDir; 
			
			savedAptMark=$(apt-mark showmanual); 
			
# build .deb files from upstream's source packages (which are verified by apt-get)
			apt-get update; 
			apt-get build-dep -y 
				postgresql-common pgdg-keyring 
				postgresql-$PG_MAJOR=$PG_VERSION 
			; 
			DEB_BUILD_OPTIONS=nocheck parallel=$(nproc) 
				apt-get source --compile 
					postgresql-common pgdg-keyring 
					postgresql-$PG_MAJOR=$PG_VERSION 
			; 
# we don't remove APT lists here because they get re-downloaded and removed later
			
# reset apt-mark's manual list so that purge --auto-remove will remove all build dependencies
# (which is done after we install the built packages so we don't have to redownload any overlapping dependencies)
			apt-mark showmanual  xargs apt-mark auto  devnull; 
			apt-mark manual $savedAptMark; 
			
# create a temporary local APT repo to install from (so that dependency resolution can be handled by APT, as it should be)
			ls -lAFh; 
			dpkg-scanpackages .  Packages; 
			grep '^Package ' Packages; 
			echo deb [ trusted=yes ] file$tempDir .  etcaptsources.list.dtemp.list; 
# work around the following APT issue by using AcquireGzipIndexes=false (overriding etcaptapt.conf.ddocker-gzip-indexes)
#   Could not open file varlibaptlistspartial_tmp_tmp.ODWljpQfkE_._Packages - open (13 Permission denied)
#   ...
#   E Failed to fetch storevarlibaptlistspartial_tmp_tmp.ODWljpQfkE_._Packages  Could not open file varlibaptlistspartial_tmp_tmp.ODWljpQfkE_._Packages - open (13 Permission denied)
			apt-get -o AcquireGzipIndexes=false update; 
			;; 
	esac; 
	
	apt-get install -y postgresql-common; 
	sed -ri 's#(create_main_cluster) .$1 = false' etcpostgresql-commoncreatecluster.conf; 
	apt-get install -y 
		postgresql-$PG_MAJOR=$PG_VERSION 
	; 
	
	rm -rf varlibaptlists; 
	
	if [ -n $tempDir ]; then 
# if we have leftovers from building, let's purge them (including extra, unnecessary build deps)
		apt-get purge -y --auto-remove; 
		rm -rf $tempDir etcaptsources.list.dtemp.list; 
	fi; 
	
# some of the steps above generate a lot of .pyc files (and setting PYTHONDONTWRITEBYTECODE beforehand doesn't propagate properly for some reason), so we clean them up manually (as long as they aren't owned by a package)
	find usr -name '.pyc' -type f -exec bash -c 'for pyc; do dpkg -S $pyc & devnull  rm -vf $pyc; done' -- '{}' +

# make the sample config easier to munge (and correct by default)
RUN set -eux; 
	dpkg-divert --add --rename --divert usrsharepostgresqlpostgresql.conf.sample.dpkg usrsharepostgresql$PG_MAJORpostgresql.conf.sample; 
	cp -v usrsharepostgresqlpostgresql.conf.sample.dpkg usrsharepostgresqlpostgresql.conf.sample; 
	ln -sv ..postgresql.conf.sample usrsharepostgresql$PG_MAJOR; 
	sed -ri s!^#(listen_addresses)s=sS+.!1 = ''! usrsharepostgresqlpostgresql.conf.sample; 
	grep -F listen_addresses = '' usrsharepostgresqlpostgresql.conf.sample

RUN mkdir -p varrunpostgresql && chown -R postgrespostgres varrunpostgresql && chmod 2777 varrunpostgresql

ENV PATH $PATHusrlibpostgresql$PG_MAJORbin
ENV PGDATA varlibpostgresqldata
RUN mkdir -p $PGDATA && chown -R postgrespostgres $PGDATA && chmod 777 $PGDATA # this 777 will be replaced by 700 at runtime (allows semi-arbitrary --user values)
VOLUME varlibpostgresqldata

COPY docker-entrypoint.sh usrlocalbin
RUN ln -s usrlocalbindocker-entrypoint.sh  # backwards compat
ENTRYPOINT [docker-entrypoint.sh]

EXPOSE 5432
CMD [postgres]