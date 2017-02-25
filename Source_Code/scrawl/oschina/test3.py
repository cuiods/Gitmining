#read > http://developer.openstack.org/firstapp-libcloud/getting_started.html
from libcloud.compute.types import Provider
from libcloud.compute.providers import get_driver
from libcloud import security
#OPENSTACK Compute > Access & Securtiy > API Accesss > Download Opensstack RC File v2.0
auth_username = 'SuperMonkey'
auth_password = 'openstack'
#auth_url is OS_AUTH_URL without /v2.0
auth_url = 'http://192.168.8.146:5000'
#default is admin
project_name = 'demo'
#region_name is OS_REGION_NAME
region_name = 'RegionOne'

provider = get_driver(Provider.OPENSTACK)
conn = provider(auth_username,
                auth_password,
                ex_force_auth_url=auth_url,
                ex_force_auth_version='2.0_password',
                ex_tenant_name=project_name,
                ex_force_service_region=region_name)
flavor_id = 'c1'
instance_name = 'Banana'





